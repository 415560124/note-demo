package com.rhy.note.hadoop.mapreduce.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import javax.validation.constraints.Null;
import java.io.IOException;

public class MapperImpl extends Mapper<Object, Text, IntWritable, NullWritable> {
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        IntWritable intWritable = new IntWritable(Integer.valueOf(value.toString()));
        context.write(intWritable, NullWritable.get());
    }
}
