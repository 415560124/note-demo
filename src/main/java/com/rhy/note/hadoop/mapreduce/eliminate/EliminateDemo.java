package com.rhy.note.hadoop.mapreduce.eliminate;

import com.rhy.note.hadoop.mapreduce.partition.MapperImpl;
import com.rhy.note.hadoop.mapreduce.partition.PartitionDemo;
import com.rhy.note.hadoop.mapreduce.partition.ReducerImpl;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class EliminateDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration configuration = new Configuration();
        //这句话很关键
        configuration.set("mapred.job.tracker","master:9001");
        Job job = Job.getInstance(configuration,"EliminateDemo");
        job.setUser("root");
        job.setJarByClass(EliminateDemo.class);
        job.setMapperClass(MapperImpl.class);
        job.setCombinerClass(ReducerImpl.class);
        job.setReducerClass(ReducerImpl.class);
        //设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileOutputFormat.setOutputPath(job,new Path(args[0]));
        for (int i = 1; i < args.length; i++) {
            FileInputFormat.addInputPath(job,new Path(args[i]));
        }
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
