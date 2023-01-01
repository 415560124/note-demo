package com.rhy.note.hadoop.mapreduce.partition;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionerImpl extends Partitioner<RectangleWritable, NullWritable> {
    @Override
    public int getPartition(RectangleWritable rectangleWritable, NullWritable nullWritable, int numPartitions) {
        if(rectangleWritable.getLength() == rectangleWritable.getWidth()){
            //正方形
            return 0;
        }
        //长方形
        return 1;
    }
}
