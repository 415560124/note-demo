package com.rhy.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SpringBootApplication
public class NoteDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(NoteDemoApplication.class, args);
//        List<CustomA> customAList = new ArrayList<>();
//        //表总数
//        int tableNum = 7;
//        //循环查询出来的数据
//        int total = 0;
//        for (int i = 0; i < customAList.size(); i++) {
//            total = total + (customAList.get(i).total/tableNum);
//        }
//        //算出总天数(结束日期-开始日期)
//        int dateTotal = 20 - 18;
//        //计算平均值(小数)
//        int avg = total / dateTotal;
//        //乘以100分
//        int fenshu = 100 * avg;
    }

//    public static void main(String[] args) {
//        //每个表的总分数
//        Map<String,Integer> tableTotal= new HashMap<>();
////        SpringApplication.run(NoteDemoApplication.class, args);
//        List<CustomA> customAList = new ArrayList<>();
//        //循环查询出来的数据
//        for (CustomA customA: customAList) {
//            //按照表单个加 - 一个表这几天的总得分
//            Integer total = tableTotal.get(customA.TABLE_NAME);
//            total = total + customAList.get(i).total;
//            tableTotal.put(customA.TABLE_NAME,total);
//        }
//        //算出总天数(结束日期-开始日期)
//        int dateTotal = 20 - 18;
//        //计算每个表的平均值(小数)
//        Map<String,Integer> tableAvg = new HashMap<>();
//        for (String s : tableTotal.keySet()) {
//            //一个表这几天的总得分
//            Integer total = tableTotal.get(s);
//            //一个表的平均分
//            //乘以100分
//            tableAvg.put(s,total/dateTotal*100);
//        }
//        //表总数
//        int tableNum = 7;
//        //全面性所有表总计分数
//        int tableTotalNum = 0;
//        //计算全面性的平均分
//        for (String s : tableAvg.keySet()) {
//            //一个表的平均分
//            Integer avg = tableAvg.get(s);
//            //计算所有表的总分
//            tableTotalNum = tableTotalNum + avg;
//        }
//        //全面性得分
//        int fenshu =tableTotalNum/tableNum;
//    }
}
