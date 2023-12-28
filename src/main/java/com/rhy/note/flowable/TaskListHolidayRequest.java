package com.rhy.note.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 执行请假审批流程
 */
public class TaskListHolidayRequest {
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
            Scanner scanner = new Scanner(System.in);
            //managers
            System.out.println("用户组？");
            String candidateGroup = scanner.nextLine();
            //获取当前managers用户组的任务列表
            TaskService taskService = processEngine.getTaskService();
            List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).list();
            System.out.println(candidateGroup+"有"+tasks.size()+"个任务");
            for (int i = 0; i < tasks.size(); i++) {
                Map<String, Object> variables = taskService.getVariables(tasks.get(i).getId());
                System.out.println((i+1)+"）"+variables.get("employee")+"的"+tasks.get(i).getName());
            }
            //进行审批
            System.out.println("选择一个要完成的任务");
            Integer taskIndex = Integer.valueOf(scanner.nextLine());
            Task task = tasks.get(taskIndex.intValue() - 1);
            //获得任务的相关信息
            Map<String, Object> processVariables = taskService.getVariables(task.getId());
            System.out.println(processVariables.get("employee") + "想要请假" +
                    processVariables.get("nrOfHolidays") + "天。理由是"+processVariables.get("description")+"。是否审批通过？Y.通过 N.驳回");
            boolean approved = scanner.nextLine().toLowerCase().equals("y");
            Map<String,Object> variables = new HashMap<>();
            variables.put("approved",approved);
            taskService.complete(task.getId(),variables);
            if(approved){
                Task next = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                taskService.complete(next.getId());
            }
        }


    }
}
