package com.rhy.note.flowable;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 执行请假审批流程
 */
public class RunHolidayRequest {
    public static void main(String[] args) {
        //初始化配置
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration();
        cfg.setJdbcUrl("jdbc:mysql://localhost:3306/flowable?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Shanghai");
        cfg.setJdbcUsername("root");
        cfg.setJdbcPassword("root");
        cfg.setJdbcDriver("com.mysql.jdbc.Driver");
        cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        while(true){
            //启动流程实例
            Scanner scanner = new Scanner(System.in);
            System.out.println("你是谁？");
            String employee = scanner.nextLine();

            System.out.println("你想请几天假？");
            Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

            System.out.println("请假理由？");
            String description = scanner.nextLine();
            //启动一个工作流，传入工作流的ID
            RuntimeService runtimeService = processEngine.getRuntimeService();
            Map<String,Object> variables = new HashMap<>();
            variables.put("employee",employee);
            variables.put("nrOfHolidays",nrOfHolidays);
            variables.put("description",description);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest",variables);
            System.out.println("创建业务流实例，ID为："+processInstance.getId()+"，名称为："+processInstance.getName());
        }

    }
}
