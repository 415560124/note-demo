package com.rhy.note.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;

import java.util.Scanner;

/**
 * 部署请假审批流程
 */
public class DeployHolidayRequest {
    public static void main(String[] args) {
        //初始化配置
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration();
        cfg.setJdbcUrl("jdbc:mysql://localhost:3306/flowable?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Shanghai");
        cfg.setJdbcUsername("root");
        cfg.setJdbcPassword("root");
        cfg.setJdbcDriver("com.mysql.jdbc.Driver");
        cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        //部署bpmn文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                //文件名和前缀中间必须有协议名
                .addClasspathResource("flowable/holiday-request.bpmn20.xml")
                .deploy();
        //通过API查询部署的业务流
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("找到业务流，名称为："+processDefinition.getName());


    }
}
