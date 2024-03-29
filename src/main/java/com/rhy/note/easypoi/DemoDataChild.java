package com.rhy.note.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 基础数据类
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ExcelTarget("demoData")
public class DemoDataChild implements java.io.Serializable {
    @Excel(name = "字符串标题",width = 50)
    private String string;
    @Excel(name = "日期标题",format = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    @Excel(name = "数字标题")
    private Double doubleData;
}
