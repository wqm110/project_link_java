package com.ruoyi.project.common;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.project.service.IProjectCommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "B项目公共接口")
@RequestMapping("/project/common")
public class ProjectCommon {

    private IProjectCommonService commonService;

    @Autowired
    public ProjectCommon(IProjectCommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * 查找某角色下的人员
     *
     * @return
     */
    @GetMapping("/getUserByRole/{roleKey}")
    @ApiImplicitParam(name = "roleKey", value = "角色的键", required = true)
    @ApiOperation(value = "查找某角色下的人员", response = AjaxResult.class)
    public AjaxResult getUsersByRole(@PathVariable("roleKey") String roleKey) {
        List<Map<String, Object>> pmList = commonService.getUsersByRole(roleKey);
        return AjaxResult.success(pmList);
    }
}
