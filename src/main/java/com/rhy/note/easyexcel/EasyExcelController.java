//package com.rhy.note.easyexcel;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.fastjson.JSON;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.*;
//
///**
// * @author: Rhy
// * @datetime: 2023/6/27 10:49
// * @description:
// */
//@RestController
//@RequestMapping("easyexcel")
//public class EasyExcelController {
//    /**
//     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
//     *
//     * @since 2.1.1
//     */
//    @GetMapping("downloadFailedUsingJson")
//    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-disposition", "attachment;filename=demo.xlsx");
//        EasyExcel.write(response.getOutputStream(), DemoData.class).sheet("模板").doWrite(data());
//    }
//    private List<DemoData> data() {
//        List<DemoData> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            DemoData data = new DemoData();
//            data.setString("字符串" + i);
//            data.setDate(new Date());
//            data.setDoubleData(0.56);
//            list.add(data);
//        }
//        return list;
//    }
//}
