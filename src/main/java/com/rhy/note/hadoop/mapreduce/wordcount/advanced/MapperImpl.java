package com.rhy.note.hadoop.mapreduce.wordcount.advanced;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class MapperImpl extends Mapper<Object, Text,Text, IntWritable> {
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        Text keyOut;
        IntWritable valueOut = new IntWritable(1);
        StringTokenizer token = new StringTokenizer(value.toString());
        while (token.hasMoreTokens()){
            keyOut = new Text(token.nextToken());
            context.write(keyOut,valueOut);
        }
    }
}
