package com.ruoyi.web.controller.camunda;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.*;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "A_Camunda测试接口")
public class CamundaTestController {
    private IdentityService identityService;

    @Autowired
    public CamundaTestController(IdentityService identityService) {
        this.identityService = identityService;
    }
    @ApiOperationSupport(author = "wqm")
    @GetMapping("/getServices/{pname}")
    public AjaxResult testGetService(@PathVariable("pname") String pname) {
        HashMap<Object, Object> map = new HashMap<>();

        ProcessEngine engine = ProcessEngines.getProcessEngine("default");
//        ProcessEngineConfigurationImpl config = (ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration();
//        config = config.setProcessEngineName("test");
        String name = engine.getName();
        AuthorizationService authorizationService = engine.getAuthorizationService();
        DecisionService decisionService = engine.getDecisionService();

        map.put("identityService", identityService);
        map.put("authorizationService", identityService);
        System.out.println("name = " + name + "\t");
        System.out.println("decisionService = " + decisionService);
        System.out.println("authorizationService = " + authorizationService);

        return AjaxResult.success(map);

    }
    @ApiOperationSupport(order = 1)
    @GetMapping("/identityService/userlist")
    public AjaxResult getUserLIst(UserEntity userEntity) {
        /**
         * Starting command -------------------- CreateUserQueryCmd ----------------------
         Starting command -------------------- CreateUserQueryCmd ----------------------
         opening new command context
         opening new command context
         closing existing command context
         closing existing command context
         Finishing command -------------------- CreateUserQueryCmd ----------------------
         Finishing command -------------------- CreateUserQueryCmd ----------------------
         Starting command -------------------- DbUserQueryImpl ----------------------
         Starting command -------------------- DbUserQueryImpl ----------------------
         opening new command context
         opening new command context
         select distinct RES.* from ACT_ID_USER RES WHERE RES.FIRST_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
         select distinct RES.* from ACT_ID_USER RES WHERE RES.FIRST_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
         - ==> Parameters: 张(String), 2147483647(Integer), 0(Integer)
         - ==> Parameters: 张(String), 2147483647(Integer), 0(Integer)
         - <==      Total: 1
         closing existing command context
         closing existing command context
         Cache state after flush: [         PERSISTENT UserEntity[zhangsan] ]
         Cache state after flush: [  PERSISTENT UserEntity[zhangsan] ]
         Finishing command -------------------- DbUserQueryImpl ----------------------
         Finishing command -------------------- DbUserQueryImpl ----------------------*/
        UserQuery userQuery = identityService.createUserQuery();
        List<User> list = userQuery.userFirstName(userEntity.getFirstName()).list();
        return AjaxResult.success(list);
    }

    @PostMapping("/identityService/addUser")
    @ApiImplicitParam(name = "userEntity", value = "用户对象", required = true, example = "{\n" +
            "    \"id\":\"zhangsan\",\n" +
            "    \"firstName\":\"张\",\n" +
            "    \"lastName\":\"三\",\n" +
            "    \"password\":\"1\",\n" +
            "    \"email\":\"110@123.com\",\n" +
            "    \"revision\":\"1\",\n" +
            "    \"persistentState\":\"1\",\n" +
            "    \"revisionNext\":\"1\",\n" +
            "    \"lockExpirationTime\":\"\",\n" +
            "    \"attempts\":\"\",\n" +
            "    \"salt\":\"1\" \n" +
            "}")
    @ApiResponse(code = 200, message = "请求成功", examples = @Example(value = @ExampleProperty(value = "[]", mediaType = "list")))
    @Transactional
    public AjaxResult createOrUpdateUser(@RequestBody UserEntity userEntity) {
        identityService.saveUser(userEntity);
        return AjaxResult.success();
    }
}
