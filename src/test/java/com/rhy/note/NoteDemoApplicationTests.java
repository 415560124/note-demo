package com.rhy.note;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class NoteDemoApplicationTests {

    @Test
    void contextLoads() {
        List<CountObject> countObjectList = new ArrayList<CountObject>(){
            {
                add(new CountObject().setType(1).setTotal(9).setOrgId("1").setOrgName("中心医院1"));
                add(new CountObject().setType(0).setTotal(81).setOrgId("1").setOrgName("中心医院1"));
                add(new CountObject().setType(1).setTotal(70).setOrgId("2").setOrgName("中心医院2"));
                add(new CountObject().setType(1).setTotal(66).setOrgId("3").setOrgName("中心医院3"));
            }
        };
        List<CountObject> countObjectList1 = new ArrayList<CountObject>(){
            {
                add(new CountObject().setType(1).setTotal(9).setOrgId("1").setOrgName("中心医院1"));
                add(new CountObject().setType(0).setTotal(81).setOrgId("1").setOrgName("中心医院1"));
                add(new CountObject().setType(1).setTotal(70).setOrgId("2").setOrgName("中心医院2"));
                add(new CountObject().setType(1).setTotal(66).setOrgId("3").setOrgName("中心医院3"));
            }
        };
        List<CountObject> countObjectList2 = new ArrayList<CountObject>(){
            {
                add(new CountObject().setType(1).setTotal(9).setOrgId("1").setOrgName("中心医院1"));
                add(new CountObject().setType(0).setTotal(81).setOrgId("1").setOrgName("中心医院1"));
                add(new CountObject().setType(1).setTotal(70).setOrgId("2").setOrgName("中心医院2"));
                add(new CountObject().setType(1).setTotal(66).setOrgId("3").setOrgName("中心医院3"));
            }
        };
        //返回的数据List集合
        List<CountResult> countResults = new ArrayList<>();
        //存 机构 - 医院一行记录的对象 缓存，用来能够通过机构ID快速查找到对应返回的数据对象作用
        Map<String,CountResult> countResultMap = new HashMap<>();
        countObjectList.stream().forEach((countObject)->{
            CountResult countResult = null;
            //返回的数据集中是否存在本机构(即之前是否添加过这个)
            Boolean is = countResultMap.containsKey(countObject.getOrgId());
            if(is){
                //已存在则把行数据取出来
                countResult = countResultMap.get(countObject.getOrgId());
            }else{
                //没添加过则创建新对象
                countResult = new CountResult();
            }

            if(countObject.getType() == 0){
                //设置档案未使用总数
                countResult.setUnused(countObject.getTotal());
                //重新计算总数
                countResult.setTotal(countResult.getTotal() + countResult.getUnused());
            }else if(countObject.getType() == 1){
                //设置档案已使用总数
                countResult.setUsed(countObject.getTotal());
                //重新计算总数
                countResult.setTotal(countResult.getTotal() + countResult.getUsed());
            }
            //添加到返回集合和缓存Map中
            if(!is){
                countResult.setOrgName(countObject.getOrgName());
                countResults.add(countResult);
                countResultMap.put(countObject.getOrgId(),countResult);
            }
        });
        System.out.println(countResults);
        countObjectList1.stream().forEach((countObject)->{
            CountResult countResult = null;
            //返回的数据集中是否存在本机构(即之前是否添加过这个)
            Boolean is = countResultMap.containsKey(countObject.getOrgId());
            if(is){
                //已存在则把行数据取出来
                countResult = countResultMap.get(countObject.getOrgId());
            }else{
                //没添加过则创建新对象
                countResult = new CountResult();
            }

            if(countObject.getType() == 0){
                //设置档案未使用总数
                countResult.setUnused(countObject.getTotal());
                //重新计算总数
                countResult.setTotal(countResult.getTotal() + countResult.getUnused());
            }else if(countObject.getType() == 1){
                //设置档案已使用总数
                countResult.setUsed(countObject.getTotal());
                //重新计算总数
                countResult.setTotal(countResult.getTotal() + countResult.getUsed());
            }
            //添加到返回集合和缓存Map中
            if(!is){
                countResult.setOrgName(countObject.getOrgName());
                countResults.add(countResult);
                countResultMap.put(countObject.getOrgId(),countResult);
            }
        });
    }

}
