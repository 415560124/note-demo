package com.rhy.note.hadoop.mapreduce.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MapperImpl extends Mapper<LongWritable, Text,RectangleWritable, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //按空格拆分
        String[] s = value.toString().split(" ");
        RectangleWritable rectangleWritable = new RectangleWritable(Integer.valueOf(s[0]),Integer.valueOf(s[1]));
        context.write(rectangleWritable,NullWritable.get());
    }
}
