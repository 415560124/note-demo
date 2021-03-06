package com.rhy.note;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
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
    @Test
    void contextLoads1() throws IOException {
        //通过流读取Excel文件
        FileInputStream fileInputStream= new FileInputStream("C:\\Users\\41556\\Desktop\\test.xlsx");
        //2.通过poi解析流 HSSFWorkbook 处理流得到的对象中 就封装了Excel文件所有的数据
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        //获取表格中的sheet页数量
        int numberOfSheets = workbook.getNumberOfSheets();
        //FORMULA类型值读取器
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        for(int i=0;i<numberOfSheets;i++){
            //3.从文件中获取表对象  getSheetAt通过下标获取
            XSSFSheet sheetAt = workbook.getSheetAt(i);
            //4.从表中获取到行数据  从第二行开始 到 最后一行  getLastRowNum() 获取最后一行的下标
            int lastRowNum = sheetAt.getLastRowNum();
            for (int j = 1; j <= lastRowNum; j++) {
                //通过下标获取行
                XSSFRow row = sheetAt.getRow(j);
                //获得行中的列总数
                short lastCellNum = row.getLastCellNum();
                //固定模板
//                XSSFCell cell = row.getCell(0);
//                String stringCellValue = cell.getStringCellValue();
                //自定义可变式
                for (int k = 0; k < lastCellNum; k++) {
                    XSSFCell cell = row.getCell(k);
                    //从行中获取数据
                    switch (cell.getCellType()){
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println(i+"-"+j+"-"+k+"-"+row.getCell(k).getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            System.out.println(i+"-"+j+"-"+k+"-"+cell.getNumericCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            System.out.println(i+"-"+j+"-"+k+"-"+cell.getStringCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            System.out.println(i+"-"+j+"-"+k+"-"+row.getCell(k).getErrorCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            System.out.println(i+"-"+j+"-"+k+"-"+evaluator.evaluate(row.getCell(k)).getNumberValue());
                            break;
                    }


                }
            }

        }
        System.out.println(1);
    }

}
