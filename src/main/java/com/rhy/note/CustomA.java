package com.rhy.note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Herion Lemon
 * @date: 2021/5/20 21:33
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
public class CustomA {
    String BUS_TIME;
    String TABLE_NAME;
    int total;
    public static void main(String[] args) {
        List<String> collection1 = new ArrayList<>();
        collection1.add("A");
        collection1.add("B");
        collection1.add("C");
        collection1.add("D");

        List<String> collection2 = new ArrayList<>();
        collection2.add("C");
        collection2.add("D");
        collection2.add("E");

        List<String> result = collection1.stream().filter(s -> !collection2.contains(s)).collect(Collectors.toList());

        System.out.println("筛选后的集合：" + result);
    }
}
