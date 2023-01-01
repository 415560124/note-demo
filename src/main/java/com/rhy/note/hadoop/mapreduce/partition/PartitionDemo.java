package com.rhy.note.hadoop.mapreduce.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.File;
import java.io.IOException;

public class PartitionDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration,"PartitionDemo");
        job.setUser("root");
        job.setJarByClass(PartitionDemo.class);
        job.setMapperClass(MapperImpl.class);
        job.setReducerClass(ReducerImpl.class);
        job.setNumReduceTasks(2);
        job.setPartitionerClass(PartitionerImpl.class);
        FileOutputFormat.setOutputPath(job,new Path(args[0]));
        for (int i = 1; i < args.length; i++) {
            FileInputFormat.addInputPath(job,new Path(args[i]));
        }
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
