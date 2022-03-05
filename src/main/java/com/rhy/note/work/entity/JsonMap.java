package com.rhy.note.work.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author: Herion Lemon
 * @date: 2021年02月25日 09:57:00
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description: 测试对象
 */
@Data
@Accessors(chain = true)
public class JsonMap {
    private Map<String,Object> map;

}
