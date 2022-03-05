package com.rhy.note.work;

import com.alibaba.fastjson.JSONObject;
import com.rhy.note.work.entity.JsonList;
import com.rhy.note.work.entity.JsonMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: Herion Lemon
 * @date: 2021年02月25日 09:56:00
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description: 测试JSON序列化
 */
public class TestJson {

    public static void main(String[] args) {
        JsonList jsonList = new JsonList();
        jsonList.setRows(new ArrayList<JsonMap>(){
            {
                add(
                        new JsonMap().setMap(new HashMap<String,Object>(){
                            {
                                put("1","1");
                                put("2","2");
                            }
                        })
                );
                add(
                        new JsonMap().setMap(new HashMap<String,Object>(){
                            {
                                put("1","1");
                                put("2","2");
                            }
                        })
                );
            }
        });
        System.out.println(JSONObject.toJSONString(jsonList));

    }
}
