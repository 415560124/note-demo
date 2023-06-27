package com.rhy.note.hadoop.mapreduce.avg;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class ReducerImpl extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //学科数
        int len = 0;
        //总分
        int total = 0;
        Iterator<IntWritable> iterator = values.iterator();
        while (iterator.hasNext()){
            len++;
            total += iterator.next().get();
        }
        //平均成绩
        int avg = total / len;
        context.write(key,new IntWritable(avg));
    }
}
