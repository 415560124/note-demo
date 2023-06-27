package com.rhy.note.hadoop.mapreduce.avg;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.StringTokenizer;

public class MapperImpl extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //对输入的数据按行进行分割
        StringTokenizer stringTokenizer = new StringTokenizer(value.toString(),"\n");
        //分别处理每一行
        while (stringTokenizer.hasMoreElements()){
            //每行按空格划分
            StringTokenizer stringTokenizer1 = new StringTokenizer(stringTokenizer.nextToken());
            //姓名
            String name = stringTokenizer1.nextToken();
            //成绩
            String score = stringTokenizer1.nextToken();
            context.write(
                    new Text(name),
                    new IntWritable(Integer.valueOf(score))
            );
        }
    }
}
