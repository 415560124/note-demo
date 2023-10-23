package com.rhy.note.flowable;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricActivityInstanceQuery;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 查询已完成的工作流记录
 */
public class HistoryHolidayRequest {
    public static void main(String[] args) throws IllegalAccessException {
        //初始化配置
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration();
        cfg.setJdbcUrl("jdbc:mysql://localhost:3306/flowable?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Shanghai");
        cfg.setJdbcUsername("root");
        cfg.setJdbcPassword("root");
        cfg.setJdbcDriver("com.mysql.jdbc.Driver");
        cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        while(true) {
            Scanner scanner = new Scanner(System.in);
            //managers
            System.out.println("用户组？");
            String candidateGroup = scanner.nextLine();
            //查询已完成的流程
            List<HistoricTaskInstance> taskInstances = historyService.createHistoricTaskInstanceQuery()
                    .taskCandidateGroup(candidateGroup)
                    .finished()
                    .orderByHistoricTaskInstanceEndTime()
                    .desc()
                    .list();
            Set<String> instanceIds = taskInstances.stream().map(HistoricTaskInstance::getProcessInstanceId).collect(Collectors.toSet());
            if(CollectionUtils.isEmpty(instanceIds)){
                System.out.println("该用户组历史审批记录");
                continue;
            }
            //通过instanceId查实例记录
            List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceIds(instanceIds)
                    .finished()
                    .orderByProcessInstanceEndTime().desc().list();
            for (int i = 0; i < historicProcessInstances.size(); i++) {
                HistoricProcessInstance historicProcessInstance = historicProcessInstances.get(i);
                ApproveInfo approveInfo = new ApproveInfo();
                //查询该流程的参数
                List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery()
                        .processInstanceId(historicProcessInstance.getId())
                        .list();
                for (HistoricVariableInstance variableInstance : variableInstances) {
                    String variableName = variableInstance.getVariableName();
                    Object value = variableInstance.getValue();
                    Field[] declaredFields = ApproveInfo.class.getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        if (declaredField.getName().equals(variableName)) {
                            declaredField.setAccessible(true);
                            declaredField.set(approveInfo, value);
                        }
                    }
                }
                System.out.println(JSONObject.toJSONString(approveInfo));
            }
        }
    }
}
