package com.rhy.note.hadoop.mapreduce.wordcount.advanced;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.map.InverseMapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;


/**
 * hdfs://192.168.31.131:9000/wordCountAdvancedDemo/res
 * hdfs://192.168.31.131:9000/user/root/input/file1.txt
 * hdfs://192.168.31.131:9000/user/root/input/file2.txt
 * hdfs://192.168.31.131:9000/wordCountAdvancedDemo/res
 * hdfs://192.168.31.131:9000/wordCountAdvancedDemo/resFinal
 */
public class WordCountDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration configuration = new Configuration();
        //这句话很关键
        configuration.set("mapred.job.tracker","master:9001");
        Job job = Job.getInstance(configuration, "WordCountAdvancedDemo");
        job.setJarByClass(WordCountDemo.class);
        job.setMapperClass(MapperImpl.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(2);
        job.setCombinerClass(ReducerImpl.class);
        job.setReducerClass(ReducerImpl.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定统计作业输出格式，与排序作业的输入格式相对应
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        FileOutputFormat.setOutputPath(job,new Path(args[0]));
        FileInputFormat.addInputPath(job,new Path(args[1]));
        FileInputFormat.addInputPath(job,new Path(args[2]));
        if(job.waitForCompletion(true)){
            Job sortJob = Job.getInstance(configuration, "sortJob");
            sortJob.setJarByClass(WordCountDemo.class);
            //与排序作业的输出格式相对应
            sortJob.setInputFormatClass(SequenceFileInputFormat.class);
            //交换key-value 框架实现类
            sortJob.setMapperClass(InverseMapperImpl.class);
            sortJob.setMapOutputKeyClass(IntWritable.class);
            sortJob.setMapOutputValueClass(Text.class);
            //Hadoop默认对IntWritable按升序排序，重写IntWritable.Comparator类实现降序排列
            sortJob.setSortComparatorClass(IntWritableDescComparator.class);
            sortJob.setNumReduceTasks(1);
            sortJob.setOutputKeyClass(IntWritable.class);
            sortJob.setOutputValueClass(Text.class);
            sortJob.setOutputFormatClass(TextOutputFormat.class);
            FileInputFormat.addInputPath(sortJob,new Path(args[3]));
            FileOutputFormat.setOutputPath(sortJob,new Path(args[4]));
            System.exit(sortJob.waitForCompletion(true) ? 0 : 1);
        }
    }
}
