

# Camanda 开发文档

- [官网地址](https://camunda.com/)

- [下载地址](https://camunda.com/download/)

- [官方文档地址](https://docs.camunda.org/manual/latest/webapps/)

- [springboot 安装文档](https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/)

- 参考文章
  +  [spring 项目 集成 camunda](https://bishion.github.io/2020/02/26/camunda-spring/)


## springboot 集成 camanda

### pom

添加子模块

- 父pom：ruoyi

- 子pom：ruoyi-camunda

  -   
      ```xml
           <?xml version="1.0" encoding="UTF-8"?>
           <project xmlns="http://maven.apache.org/POM/4.0.0"
                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
               <parent>
                   <artifactId>ruoyi</artifactId>
                   <groupId>com.ruoyi</groupId>
                   <version>3.4.0</version>
               </parent>
               <modelVersion>4.0.0</modelVersion>
           <description>
               camunda 工作流
           </description>
               <artifactId>ruoyi-camunda</artifactId>
           
               <properties>
                   <maven.compiler.source>8</maven.compiler.source>
                   <maven.compiler.target>8</maven.compiler.target>
               </properties>
           
               <dependencies>
                   <dependency>
                       <groupId>com.sun.xml.bind</groupId>
                       <artifactId>jaxb-impl</artifactId>
                       <version>2.2.3</version>
                   </dependency>
                   <dependency>
                       <groupId>io.springfox</groupId>
                       <artifactId>springfox-swagger2</artifactId>
                   </dependency>
                   <!--        添加Camunda外部任务客户端依赖项-->
                   <dependency>
                       <groupId>org.camunda.bpm</groupId>
                       <artifactId>camunda-external-task-client</artifactId>
                       <version>1.4.0</version>
                   </dependency>
                   <dependency>
                       <groupId>org.camunda.bpm.springboot</groupId>
                       <artifactId>camunda-bpm-spring-boot-starter-webapp</artifactId>
                   </dependency>
           
                   <dependency>
                       <groupId>org.camunda.bpm</groupId>
                       <artifactId>camunda-engine-spring</artifactId>
                   </dependency>
                   <dependency>
                       <groupId>org.camunda.bpm.springboot</groupId>
                       <artifactId>camunda-bpm-spring-boot-starter</artifactId>
                   </dependency>
                   <dependency>
                       <groupId>io.swagger</groupId>
                       <artifactId>swagger-annotations</artifactId>
                       <version>1.5.21</version>
                       <scope>compile</scope>
                   </dependency>
                   <dependency>
                       <groupId>io.springfox</groupId>
                       <artifactId>springfox-swagger2</artifactId>
                       <version>2.10.5</version>
                   </dependency>
           
                   <dependency>
                       <groupId>org.slf4j</groupId>
                       <artifactId>slf4j-simple</artifactId>
                   </dependency>
                   <dependency>
                       <groupId>com.github.xiaoymin</groupId>
                       <artifactId>knife4j-annotations</artifactId>
                   </dependency>
               </dependencies>  
               <build>
                   <plugins>
                       <plugin>
                           <groupId>org.springframework.boot</groupId>
                           <artifactId>spring-boot-maven-plugin</artifactId>
                           <version>2.2.13.RELEASE</version>
                           <configuration>
                               <layout>ZIP</layout>
                           </configuration>
                           <executions>
                               <execution>
                                   <goals>
                                       <goal>repackage</goal>
                                   </goals>
                               </execution>
                           </executions>
                       </plugin>
                   </plugins>
               </build> 
           </project> 
    ```
  
### 配置

- application.yml

    -    ```yaml
           # camunda 工作流配置 
           camunda:
             bpm:
              admin-user:
               id: demo
               password: demo
               firstName: Demo
              filter:
               create: All tasks
              enabled: true
         ```

    - 数据源使用 若依其他模块数据源 自动创建表

###启动服务

- 添加免认证权限
        
    ```java
       package com.ruoyi.framework.config; 
       /**
       * spring security配置
       *
       * @author ruoyi
         */
         @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
         public class SecurityConfig extends WebSecurityConfigurerAdapter{
         @Override
         protected void configure(HttpSecurity httpSecurity) throws Exception{
              httpSecurity
                 .antMatchers("/camunda/**").anonymous(); 
         }
       }
   ```

### 登录web页面 

- 欢迎页[welcome](http://localhost:30013/camunda/app/welcome/default/#!/welcome)

  ![image-20210324124310690](https://tva1.sinaimg.cn/large/008eGmZEly1gouw8sgru2j31b00h7dhj.jpg)

- 驾驶舱[Cockpit

  [![image-20210324124245132](https://tva1.sinaimg.cn/large/008eGmZEly1gouw8dtta2j31gl0fkgn3.jpg)](http://localhost:30013/camunda/app/cockpit/default/#/dashboard)

- 自动跳转到[注册页面](http://localhost:30013/camunda/app/admin/default/setup/#/setup)
  + 添加用户
    + ![image-20210324095332352](https://tva1.sinaimg.cn/large/008eGmZEly1gourcboaqoj31eh0jmtam.jpg)
- [登录地址](http://localhost:30013/camunda/app/admin/default/#/login) 注册admin完成后 admin注册页面失效

    - ![image-20210324095504598](https://tva1.sinaimg.cn/large/008eGmZEly1gourdvz6h8j30mn0cw3yq.jpg)
- 登录后的界面
  - ![image-20210324100005007](https://tva1.sinaimg.cn/large/008eGmZEly1gourj3cpfqj31bg0jmjxn.jpg)
    - ![image-20210324100019024](https://tva1.sinaimg.cn/large/008eGmZEly1gourjc2r9ej31bg0jmwgw.jpg)
  - ![image-20210324100048287](https://tva1.sinaimg.cn/large/008eGmZEly1gourjufucij31gu0hlwg7.jpg)
  
### 界面介绍

- # [Users](http://localhost:30013/camunda/app/admin/default/#/users) （账户、列表、添加分组、添加租户） 
    - 用户列表[List of Users](http://localhost:30013/camunda/app/admin/default/#/users)
      
        - ![image-20210324100622349](https://tva1.sinaimg.cn/large/008eGmZEly1gourpnalyvj31gk06ot97.jpg)
    - 添加新用户[Create New User](http://localhost:30013/camunda/app/admin/default/#/user-create)
      
        - ![image-20210324100524328](https://tva1.sinaimg.cn/large/008eGmZEly1gouromthu9j31gj0f4q44.jpg)
    - 修改用户信息[My Profile](http://localhost:30013/camunda/app/admin/default/#/users/demo?tab=profile)
        - ![image-20210324100657024](https://tva1.sinaimg.cn/large/008eGmZEly1gourq8uhozj31gk091wf6.jpg)
      - 修改账户信息 ![image-20210324100724793](https://tva1.sinaimg.cn/large/008eGmZEly1gourqq5k7tj31gu0i3abr.jpg)
    - 修改分组信息![image-20210324100815122](https://tva1.sinaimg.cn/large/008eGmZEly1gourrlfvrij31gl069jrz.jpg)
    - 修改租户信息![image-20210324100907332](https://tva1.sinaimg.cn/large/008eGmZEly1gourshq85xj31gk064dgd.jpg)
    
  - ####[分组管理 Groups](http://localhost:30013/camunda/app/admin/default/#/groups) 
  
      - 列表 [List of Groups](http://localhost:30013/camunda/app/admin/default/#/groups)
        ![image-20210324102021305](https://tva1.sinaimg.cn/large/008eGmZEly1gous46rphfj31gn08bwfa.jpg) 
      - 创建[Create New Group](http://localhost:30013/camunda/app/admin/default/#/group-create) 
        ![image-20210324102129433](https://tva1.sinaimg.cn/large/008eGmZEly1gous5dcn0ej31gf08l0te.jpg) 
  - #### [Tenants租户管理](http://localhost:30013/camunda/app/admin/default/#/tenants)
  
      - 列表[List of Tenants](http://localhost:30013/camunda/app/admin/default/#/tenants)
  
        ![image-20210324102317763](https://tva1.sinaimg.cn/large/008eGmZEly1gous78s1chj31gk05sweu.jpg)
  
      - 添加[Create New Tenant](http://localhost:30013/camunda/app/admin/default/#/tenant-create)![image-20210324102354109](https://tva1.sinaimg.cn/large/008eGmZEly1gous7vr6paj31go07ogm5.jpg)
  
  - #### [权限管理 Authorizations](http://localhost:30013/camunda/app/admin/default/#/authorization)
  
    ![image-20210324102533457](https://tva1.sinaimg.cn/large/008eGmZEly1gous9lftk9j308d0fht9f.jpg)
  
    - 申请权限
  
        - 权限
            - 授权
              ![image-20210324103034308](https://tva1.sinaimg.cn/large/008eGmZEly1gouset60n0j316n05d0t9.jpg)
                - type
                - ALLOW
                - DENY
                - GLOABL
                - user/GROUP
                    - user
                    - group
            - **Permissions**
              ![image-20210324103258967](https://tva1.sinaimg.cn/large/008eGmZEly1goushbnybpj303r04za9y.jpg)
                - Update
                - read
                - Create
                - Delete
                - ResouseID 指定给那些资源授权
                - action 操作
                    - edit 编辑该条授权
                    - delete 删除该条授权
        - 批量授权 batch
        - 决策定义授权 Decision Definition Authorizations
        - 决策请求定义授权 Decision Requirements Definition Authorizations
        - 部署权限 Deployment Authorizations
        - 过滤器授权 Filter Authorizations
        - 组授权 Group Authorizations
        - Group Membership Authorizations
        - Historic Process Instance Authorizations
        - Historic Task Instance Authorizations
        - Operation Log Authorizations
        - Process Definition Authorizations
        - Process Instance Authorizations
        - Task Authorizations
      - Tenant Authorizations
        - Tenant Membership Authorizations
      - User Authorizations 
  - #### 系统管理[System](http://localhost:30013/camunda/app/admin/default/#/system?section=system-settings-general)
    - 1
    - 2
### 开发流程
#### 绘制流程图

![image-20210324134159391](https://tva1.sinaimg.cn/large/008eGmZEly1gouxxze4hhj30py0coq3m.jpg)

####  **学生报名:**

**startEvent名称为学生报名, id默认生成**

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gouy24otcxj30dg0enjs3.jpg)

**其中有个表单，表单中有一个参数recordId。 recordId指向的是学生报名成功后存储在db报名信息表中的一条数据主键.**

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gouy250mz0j30i90dr74r.jpg)

#### 提交一级审核

```java
@Api(value = "工作流测试")
@RestController
@RequestMapping("/canmunda/demo")
public class Demo {
    private RuntimeService runtimeService;

    @Autowired
    Demo(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @ApiOperation(value = "项目报名")
    @PostMapping(value = "/{projectId}/users/{userId}")
    @ApiImplicitParam(name = "projectId",value = "项目编号")
    public boolean ParticipatingProject(@PathVariable Long projectId, @PathVariable Long userId) {
        //ignore argument verify

        //save the record to db

        Long savedRecordId = 3L;
        //start a new instance of the process
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProjectProcessConstant.VAR_NAME_SCHOOL, "上海交通大学");
        variables.put(ProjectProcessConstant.VAR_NAME_STUDENT, String.valueOf(userId));
        variables.put(ProjectProcessConstant.FORM_RECORD_ID, savedRecordId);

        ProcessInstance instance = runtimeService.
                startProcessInstanceByKey(ProjectProcessConstant.PROCESS_ID, variables);
        if (instance == null) {
            return false;
        } else {
            return true;
        }
    }
}

```

Post man 请求

- 打开 前端
- ![image-20210324154647306](https://tva1.sinaimg.cn/large/008eGmZEly1gov1jtznsdj31570kcafm.jpg)

- 复制 **Authorization** 例如

  > Bearer eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjUyYWVjMDljLTMwOWUtNGFiMi04OTMyLWJhODVjZjU5ZjE2OSJ9.KeI2KduFXU4dMdTRYduL0USO_yVa2rXIZhyBFR8LRDKDoTJFA47rFotWOZGXgpYiYQAprDTDnuiWTOWjKjNb1Q

- 在knife4j 中填写 认证信息

  ![image-20210324154923907](/Users/admin/Library/Application Support/typora-user-images/image-20210324154923907.png)

  - 填写请求参数 发送请求

#### 一级审核

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gouy5xh92hj30bc0awglz.jpg)

> ${school} 可变参数 
>
> 在代码里 可以 ProjectProcessConstant.VAR_NAME_SCHOOL, "上海交通大学" 存值

####  查看待审列表

```java
@ApiOperation(value = "获取需要审批的项目申请列表")
    @GetMapping(value = "/project/approve/list")
    public @ResponseBody List<ProjectParticipateRequestRecord> getAllProjectParticipateRequest(String schoolName, Integer reviewLevel) {
 
        LOGGER.info("The school name is {}", schoolName);
        //get the taskList
        List<Task> tasks;
        if (reviewLevel.equals(1)) {
             tasks = taskService.createTaskQuery().
                taskName(ProjectProcessConstant.TASK_NAME_FIRST_LEVEL_REVIEW).
                            taskCandidateGroup(schoolName).
                            list();
        }else {
            tasks = taskService.createTaskQuery().
                    taskName(ProjectProcessConstant.TASK_NAME_SECOND_LEVEL_REVIEW).
                    taskCandidateGroup(schoolName).
                    list();
        }
```

> **以上代码中通过taskService来查询task. 首先通过taskName来查, 我们需要查询的是一级审核任务， 在流程定义中一级审核的name就是”一级审核”, 其次我们还需要通过学校名称作为UserTask的group值来过滤只获取当前学校需要审批的学生报名申请.**

> 在流程定义中一级审核任务的group是一个变量${school}(请翻上面的图), 在学生报名成功后开启一个实例时，我们将“上海交通大学”赋值给了此变量。 那么如果上图代码中请求参数schoolName不是“上海交通大学”则无法查询到任何task, 因为目前只有上海交通大学才有一名学生提交了报名申请并开启了一个流程定义实例。
>

**因为一个流程定义会有n个实例， 那么自然会有n条一级审批的user task.  为了审批时能够知道当前审批的是哪个实例的一级审批user task就需要在响应参数中返回taskId,具体作用下面再细说**

**如下图， 输入上海交通大学我们看到有一条待审批的报名申请**

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov24xeqjxj311y0myabq.jpg)

**输入其他名称时， 没有任何待审批的项目申请**

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov25ah8m7j31430llwfz.jpg)

**当作出审批时将审批结果和审批taskId作为参数发送到后端， 后端通过taskId获取对应实例的一级审批user task并提交给camunda引擎， 引擎根据审批结果及gateWay来决定走向。**

```java
@ApiOperation(value = "审批项目申请")
    @PutMapping(value = "/project/participateRequests/{taskId}")
    public boolean approveProjectParticipateRequest(@PathVariable String taskId, boolean needExtraInfo, boolean passed, String schoolName) {
        Task task = taskService.createTaskQuery().
                taskCandidateGroup(schoolName).taskId(taskId).singleResult();
        if (task == null) {
            LOGGER.error("The task not found, task id is {}", taskId);
            return false;
        }else {
            //business logic here
 
            //Into next step
            LOGGER.info("The taskId is {}", taskId);
            Map<String, Object> variables = new HashMap<>();
            variables.put(ProjectProcessConstant.FORM_EXTRA_INFO_1,  needExtraInfo);
            variables.put(ProjectProcessConstant.FORM_APPROVED_1, passed);
            taskService.complete(task.getId(), variables);
            return true;
        }
    }
```



上述代码中首先通过获取当前用户就职的学校（相当于鉴权）， 通过task的group(${school}变量)和taskId来获取指定的task.

之所以不直接使用taskId来调用taskService.complete（）方法将结果提交给camunda引擎是因为当前用户只能对他们学校的报名申请作出审批， 若传递的taskId指向的task的group属性并非“上海交通大学”会存在严重的问题。

其中FORM_EXTRA_INFO_1和FORM_APPROVED_1常量对应的一级审核task的表单， 如下图

![20180727172150589](https://tva1.sinaimg.cn/large/008eGmZEly1gov291tgosj311x0fn75l.jpg)

假设我们将extra_info_1设置为true, approved_info 任意, 我们可以在后台看到当前流程定义实例的执行情况

![20180727172158211](https://tva1.sinaimg.cn/large/008eGmZEly1gov29o5atuj314z0ijmys.jpg)

网关"是否需要额外材料"是根据extra_info_1字段来决定流程走向的， 上个步骤中我们将extra_info_1设置为了true, 那么上图中可以看到当前实例的流程走到了"上传额外材料"的user task.

 



**接下来需要有个api来获取等待指定用户上传额外材料的记录列表，如下代码**

```java
@ApiOperation(value = "获取学生需要上传额外材料的记录")
    @GetMapping(value = "/users/{userId}/extraInfo/list")
    public List<UploadExtraInfoRecord> getUploadExtraTask(Long userId) {
        List<Task> uploadExtraInfoTask =
                taskService.createTaskQuery().
                        taskAssignee(String.valueOf(userId)).
                        taskName(ProjectProcessConstant.TASK_NAME_UPLOAD_EXTRA_INFO).
                        list();
 
        List<UploadExtraInfoRecord> records = new ArrayList<>(uploadExtraInfoTask.size());
        uploadExtraInfoTask.forEach( task -> {
            UploadExtraInfoRecord record = new UploadExtraInfoRecord();
            record.setTaskId(task.getId());
 
            //the upload url of extra info is up to the variable
            record.setTheUploadUrlOfExtraInfo("www.google.com");
 
            records.add(record);
        });
 
        return records;
    }
```



**首先同样在上传额外材料的user task中有个所属用户的属性， 属性使用的是变量如下图**



![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov3ha7erij30wy097753.jpg)

**在开启实例时我们将userId变量赋值给了${student}变量， 如下图**

```java
 @ApiOperation(value = "项目报名")
    @PostMapping(value = "/{projectId}/users{userId}")
    public boolean ParticipatingProject(@PathVariable Long projectId, @PathVariable Long userId) {
        //ignore argument verify
 
        //save the record to db
 
        Long savedRecordId = 3L;
        //start a new instance of the process
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(ProjectProcessConstant.VAR_NAME_SCHOOL, "上海交通大学");
        variables.put(ProjectProcessConstant.VAR_NAME_STUDENT, String.valueOf(userId));
        variables.put(ProjectProcessConstant.FORM_RECORD_ID, savedRecordId);
 
        ProcessInstance instance = runtimeService.
                startProcessInstanceByKey(ProjectProcessConstant.PROCESS_ID, variables);
        if (instance == null) {
            return false;
        }else {
            return true;
        }
    }
```

那么获取用户需要上传额外材料的记录可以通过任务所属用户+任务名来获取。

如下代码

```java
List<Task> uploadExtraInfoTask =
                taskService.createTaskQuery().
                        taskAssignee(String.valueOf(userId)).
                        taskName(ProjectProcessConstant.TASK_NAME_UPLOAD_EXTRA_INFO).
                        list();
```




调用获取指定用户待上传额外材料列表的api来查看如下所示， 可以看到有一条记录

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov4w4wngjj313i0n4t9y.jpg)

 

同样的， 也需要一个用户上传额外材料的api, 如下图



```java
 @ApiOperation(value = "上传指定项目所需的额外资料")
@PostMapping(value = "/{projectId}/users/{userId}/extraInfo")
public boolean  uploadExtraInfo(@PathVariable Long projectId, @PathVariable Long userId,  String extraInfo, String taskId) {
        //must verify the task of the taskId pointing is belong the current user.
        Task task = taskService.createTaskQuery().
                taskAssignee(String.valueOf(userId)).
                taskName(ProjectProcessConstant.TASK_NAME_UPLOAD_EXTRA_INFO).
                taskId(taskId).
                singleResult();
        if (task == null) {
            LOGGER.error("The task not found.");
            LOGGER.error("the assignee is {}, taskName is {}, taskId is {}.", userId, ProjectProcessConstant.TASK_NAME_UPLOAD_EXTRA_INFO, taskId);
            return false;
        }else {
            //upload extra info to db.
      //business logic here
 
        //into next step
        taskService.complete(task.getId());
        return true;
    }
}
```
同样通过assIgnee及taskName及taskId来拿到task对象，  并提交该task使得camunda引擎继续工作。

 

上传额外材料完毕后的流程如下：

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov4x4u7hfj30z30gct9n.jpg)

又返回到了一级审核， 这次一级审核中我们将extra_info_1设置为false, approved_1设置为true, 如下图

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov4xtqz87j30yx0gl0to.jpg)

可以看到流程已经走到了二级审核， 二级审核的group是教务处，并没有使用变量。


我们通过api来查询下教务处需要审批的报名申请， 如下图

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov4y0oyu1j31080my3zr.jpg)




二级审批表单如下

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov4y8jh4lj30u40cf750.jpg)

同样有两个参数， 意义与一级审核中的一致。 在一级审核中提交的表单数据只会被保留， 若在二级审核中没有更改则会继续向下传递， 若更改了数据则传递更改后的数据。

 

这次我们将extra_info_1设置为false, approved_1设置为true来看下流程的走向.

![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov4ye9u9xj318g0e0dgh.jpg)

此时查看后台发现没有实例在运行， 这表明之前存在的流程定义实例已经执行完毕。

我们看到二级审批通过后需要执行一个serviceTask

 ![img](https://tva1.sinaimg.cn/large/008eGmZEly1gov4ymp7ofj30qp0hkabb.jpg)

下面看看serviceTask. 其中service的实现方式是Delegate expression(委托表达式), 值为${smsServiceTask}, 这表明的是由spring的smsServiceTask这个 Bean来执行当前的service

 



smsServiceTask如下

```java
package org.camunda.bpm.getstarted.loanapproval.camunda.tasks.service;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsServiceTask implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(SmsServiceTask.class);

    private final TaskService taskService;
     
    public SmsServiceTask(TaskService taskService) {
        this.taskService = taskService;
    }
     
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> variables = delegateExecution.getVariables();
        log.info("variables is {}", variables);
     
        String studentId = (String)variables.get("student");
        log.info("success send sms message to the student {}", studentId);
    }

}
```




需要实现JavaDelegate类并重写excute方法, 具体请查阅此官放doc https://docs.camunda.org/manual/7.7/user-guide/process-engine/delegation-code/




当之前流程实例实例的二级审批执行完毕且表单中值extra_info_1 = false, approved_1 = true时控制台打印如下



这表明执行到短信通知学生这个serviceTask时对应的实现被自动调用了， 调用成功后继续执行， 最后执行到了endEvent, 此时结束该实例表明流程执行完毕.

 

QA
1.应用部署后，怎么在不重启应用的情况下更新已经部署的流程定义?

简单来说，可以通过restApi来同正在工作中的引擎交互， 具体的api可到官网查询。

比如当前demo中流程定义的key为project, 那么在同样key的情况下我可以发布一个新的流程定义， camunda引擎会根据key的不同自动新增或更新流程定义。 当Java应用通过key启动一个更新后的流程定义实例时，创建的是更新后的流程定义实例。

具体文档如下：https://docs.camunda.org/manual/7.5/user-guide/process-engine/process-versioning/
