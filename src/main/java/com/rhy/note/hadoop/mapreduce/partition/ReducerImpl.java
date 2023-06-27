package com.rhy.note.hadoop.mapreduce.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerImpl extends Reducer<RectangleWritable, NullWritable, IntWritable,IntWritable> {
    @Override
    protected void reduce(RectangleWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(new IntWritable(key.getLength()),new IntWritable(key.getWidth()));
    }
}
