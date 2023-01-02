package com.rhy.note.hadoop.mapreduce.datatype;

import org.apache.hadoop.io.*;
import org.apache.poi.ss.formula.functions.T;

public class HadoopDataType {
    public static void testText(){
        System.out.println("testText");
        Text text = new Text("hello hadoop！");
        System.out.println(text.getLength());
        System.out.println(text.find("a"));
        System.out.println(text.toString());
    }
    public static void testArrayWritable(){
        System.out.println("testArrayWritable");
        ArrayWritable arrayWritable = new ArrayWritable(IntWritable.class);
        IntWritable year = new IntWritable(1996);
        IntWritable month = new IntWritable(03);
        IntWritable date = new IntWritable(07);
        arrayWritable.set(new IntWritable[]{year,month,date});
        System.out.println(String.format("%d年%d月%d日",
                ((IntWritable)arrayWritable.get()[0]).get(),
                ((IntWritable)arrayWritable.get()[1]).get(),
                ((IntWritable)arrayWritable.get()[2]).get())
        );
    }

    public static void testMapWritable(){
        System.out.println("testMapWritable");
        MapWritable mapWritable = new MapWritable();
        Text k1 = new Text("name");
        Text k2 = new Text("Rhy");
        Text k3 =new Text("age");
        mapWritable.put(k1,k2);
        mapWritable.put(k3, NullWritable.get());
        System.out.println(mapWritable.get(k1).toString());
        System.out.println(mapWritable.get(new Text("name")).toString());
        System.out.println(mapWritable.get(k3).toString());
    }

    public static void main(String[] args) {
        testText();
        testArrayWritable();
        testMapWritable();
    }
}
