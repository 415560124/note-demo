package com.rhy.note.hadoop.mapreduce.sort;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * hdfs://192.168.31.131:9000/sortDemoo/res.txt
 * hdfs://192.168.31.131:9000/user/root/input/sort1.txt
 * hdfs://192.168.31.131:9000/user/root/input/sort2.txt
 * hdfs://192.168.31.131:9000/user/root/input/sort3.txt
 */
public class SortDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        //这句话很关键
        configuration.set("mapred.job.tracker","master:9001");
        Job job = Job.getInstance(configuration, "SortDemo");
        job.setJarByClass(SortDemo.class);
        job.setMapperClass(MapperImpl.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setReducerClass(ReducerImpl.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);
        FileOutputFormat.setOutputPath(job,new Path(args[0]));
        for (int i = 1; i < args.length; i++) {
            FileInputFormat.addInputPath(job,new Path(args[i]));
        }
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
