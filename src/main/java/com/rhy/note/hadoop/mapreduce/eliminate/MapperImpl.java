package com.rhy.note.hadoop.mapreduce.eliminate;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperImpl extends Mapper<Object, Text,Text, NullWritable> {
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        context.write(value,NullWritable.get());
    }
}
