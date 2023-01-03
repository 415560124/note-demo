package com.rhy.note.hadoop.mapreduce.wordcount.advanced;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.map.InverseMapper;

import java.io.IOException;
import java.util.StringTokenizer;


public class InverseMapperImpl extends InverseMapper<Text,IntWritable> {

}
