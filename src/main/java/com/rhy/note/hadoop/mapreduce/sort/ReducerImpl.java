package com.rhy.note.hadoop.mapreduce.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerImpl extends Reducer<IntWritable, NullWritable,IntWritable,IntWritable> {
    private static IntWritable lineNum = new IntWritable(1);
    @Override
    protected void reduce(IntWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        for (NullWritable value : values) {
            context.write(lineNum,key);
            lineNum.set(lineNum.get()+1);
        }
    }
}
