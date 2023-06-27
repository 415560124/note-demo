package com.rhy.note.easypoi;//package com.rhy.note.easyexcel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.export.ExcelExportServer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author: Rhy
 * @datetime: 2023/6/27 10:49
 * @description:
 */
@RestController
@RequestMapping("easypoi")
public class EasyPoiController {
    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @GetMapping("downloadFailedUsingJson")
    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
        ExportParams exportParams = new ExportParams();
        Workbook workbook = exportExcel(exportParams, DemoData.class, data());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试.xlsx", "UTF-8") );
        workbook.write(response.getOutputStream());
    }
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
    /**
     * @param entity    表格标题属性
     * @param pojoClass Excel对象Class
     * @param dataSet   Excel对象数据List
     */
    public static Workbook exportExcel(ExportParams entity, Class<?> pojoClass,
                                       Collection<?> dataSet) {
        Workbook workbook = ExcelExportUtil.exportExcel(entity, pojoClass, dataSet);
        new ExcelExportServer().createSheet(workbook, entity, pojoClass, dataSet);
        return workbook;
    }
}
