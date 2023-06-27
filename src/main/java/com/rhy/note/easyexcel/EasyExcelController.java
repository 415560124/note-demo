package com.rhy.note.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author: Rhy
 * @datetime: 2023/6/27 10:49
 * @description:
 */
@RestController
@RequestMapping("easyexcel")
public class EasyExcelController {
    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @GetMapping("downloadFailedUsingJson")
    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), DemoData.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(data());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
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
}
