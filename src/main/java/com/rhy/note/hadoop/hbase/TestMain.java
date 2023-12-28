package com.rhy.note.hadoop.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author: Rhy
 * @datetime: 2023/8/17 19:55
 * @description:
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        Configuration hbConfig;
        hbConfig = HBaseConfiguration.create();
        hbConfig.set("hbase.zookeeper.quorum", "172.16.2.223,172.16.2.224,172.16.2.225");
        int prefixNumber = HashUtils.BKDRHash("11VKF5N00203QW", 131) % 200;
        String hashStr = String.valueOf(prefixNumber);
        if (prefixNumber < 10) {
            hashStr = "00" + hashStr;
        } else if (prefixNumber < 100) {
            hashStr = "0" + hashStr;
        }
        try (Connection connection = ConnectionFactory.createConnection(hbConfig);
             Table table = connection.getTable(TableName.valueOf("ayComputationResultV2Test"))) {

            // 创建一个前缀过滤器
            RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL,new BinaryPrefixComparator(Bytes.toBytes(hashStr + "|11VKF5N00203QW|")));

            // 创建 Scan 对象并添加前缀过滤器
            Scan scan = new Scan();
            scan.setFilter(rowFilter);

            // 执行查询并处理结果
            try (ResultScanner scanner = table.getScanner(scan)) {
                for (Result result : scanner) {
                    // 处理查询结果
                    // 每个 result 代表一行数据
                    // 使用 result.getValue(columnFamily, columnQualifier) 获取具体的列值
                    System.out.println(result.listCells());
                }
            }
        }
    }
}
