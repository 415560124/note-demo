package com.rhy.note.hadoop.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount {
    /**
     * Map实现类
     * 负责处理从文件读取到的数据处理流程
     * 四个泛型分别是：入参key泛型、入参value泛型、出参key泛型、出参value泛型
     */
    public static class TokenizerMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //单词出现次数初始 1
            IntWritable valueOut = new IntWritable(1);
            //构建一个用来解析输入value值的StringTokenizer对象
            //Java默认分隔符是“空格”、“制表符\t”、“换行符\n”、“回车符\r”
            //用这个来拆分一行数据的单词
            StringTokenizer token = new StringTokenizer(value.toString());
            Text keyOut;
            while (token.hasMoreTokens()){
                //返回从当前位置到下一个分隔符的字符串
                keyOut = new Text(token.nextToken());
                context.write(keyOut,valueOut);
            }
        }
    }

    /**
     * Reduce实现类
     * 负责处理从Map处理后输出的数据
     * 四个泛型分别是：入参key泛型、入参value泛型、出参key泛型、出参value泛型
     */
    public static class IntSumReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            context.write(key,new IntWritable(sum));
        }
    }

    /**
     * 执行MapReducer任务
     * @param outputPath 输出文件目录
     * @param inputPaths 输入文件目录
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    public static void doing(String outputPath,String... inputPaths) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration,"WordCount");
        job.setUser("root");
        //执行程序class
        job.setJarByClass(WordCount.class);
        //Map的实现类
        job.setMapperClass(TokenizerMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //Combiner的实现类
        job.setCombinerClass(IntSumReducer.class);
        //Reducer的实现类
        job.setReducerClass(IntSumReducer.class);
        //输出key类型(和Reducer的输出对上)
        job.setOutputKeyClass(Text.class);
        //输出value类型(和Reducer的输出对上)
        job.setOutputKeyClass(IntWritable.class);
        for (String inputPath : inputPaths) {
            FileInputFormat.addInputPath(job,new Path(inputPath));
        }
        FileOutputFormat.setOutputPath(job,new Path(outputPath));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        doing("hdfs://192.168.31.131:9000/output/wordcount.txt","hdfs://192.168.31.131:9000/user/root/input/file1.txt","hdfs://192.168.31.131:9000/user/root/input/file2.txt");
    }
}
