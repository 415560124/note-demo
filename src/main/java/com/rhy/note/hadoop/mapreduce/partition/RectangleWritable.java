package com.rhy.note.hadoop.mapreduce.partition;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class RectangleWritable implements WritableComparable {
    private int length;
    private int width;

    public RectangleWritable() {
    }

    public RectangleWritable(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public int compareTo(Object o) {
        RectangleWritable rectangleWritable = (RectangleWritable) o;
        if(this.getAcreage() > rectangleWritable.getAcreage()){
            return 1;
        }
        if(this.getAcreage() < rectangleWritable.getAcreage()){
            return -1;
        }
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(length);
        out.writeInt(width);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        length = in.readInt();
        width = in.readInt();
    }

    public int getAcreage(){
        return length * width;
    }
}
