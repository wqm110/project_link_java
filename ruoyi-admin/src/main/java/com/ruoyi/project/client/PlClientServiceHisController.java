package com.ruoyi.project.client;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.client.domain.PlClientServiceHis;
import com.ruoyi.project.client.service.IPlClientServiceHisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 客户跟进记录Controller
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@Api(tags = "A客户跟进记录")
@RestController
@RequestMapping("/project/client/his")
public class PlClientServiceHisController extends BaseController {
    @Autowired
    private IPlClientServiceHisService plClientServiceHisService;


    /**
     * 根据用户编号查询历史记录
     */
    @PreAuthorize("@ss.hasPermi('project:client:his:list')")
    @GetMapping("/list/{clientIds}")
    @ApiOperation(value = "根据用户编号查询历史记录")
    @ApiImplicitParam(name = "hisIds", value = "多个历史记录id")
    public TableDataInfo listByUserid(@PathVariable("clientIds") Long[] clientIds) {
        ArrayList<Long> ids = new ArrayList<>(Arrays.asList(clientIds));
        startPage();
        List<PlClientServiceHis> list = plClientServiceHisService.selectPlClientServiceHisListByIds(ids);
        return getDataTable(list);
    }

    /**
     * 查询客户跟进记录列表
     */
    @PreAuthorize("@ss.hasPermi('project:client:his:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询客户跟进记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServiceHis", value = "客户跟进记录查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlClientServiceHis plClientServiceHis) {
        String beginTime = plClientServiceHis.getBeginTime();
        startPage();
        List<PlClientServiceHis> list = plClientServiceHisService.selectPlClientServiceHisList(plClientServiceHis);
        return getDataTable(list);
    }

    /**
     * 导出客户跟进记录列表
     */
    @ApiOperation(value = "导出客户跟进记录列表")
    @PreAuthorize("@ss.hasPermi('project:client:his:export')")
    @Log(title = "客户跟进记录", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServiceHis", value = "客户跟进记录查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlClientServiceHis plClientServiceHis) {
        List<PlClientServiceHis> list = plClientServiceHisService.selectPlClientServiceHisList(plClientServiceHis);
        ExcelUtil<PlClientServiceHis> util = new ExcelUtil<PlClientServiceHis>(PlClientServiceHis.class);
        return util.exportExcel(list, "his");
    }

    /**
     * 获取客户跟进记录详细信息
     */
    @ApiOperation(value = "获取客户跟进记录详细信息")
    @PreAuthorize("@ss.hasPermi('project:client:his:query')")
    @GetMapping(value = "/{serviceHisId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceHisId", value = "客户跟进记录ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("serviceHisId") Long serviceHisId) {
        return AjaxResult.success(plClientServiceHisService.selectPlClientServiceHisById(serviceHisId));
    }

    /**
     * 新增客户跟进记录
     */
    @ApiOperation(value = "新增客户跟进记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServiceHis", value = "客户跟进记录对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:client:his:add')")
    @Log(title = "客户跟进记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlClientServiceHis plClientServiceHis) {
        LoginUser user = SecurityUtils.getLoginUser();
        if (plClientServiceHis.getUserId() == null ) {
            plClientServiceHis.setUserId(user.getUser().getUserId());
        }
        plClientServiceHis.setServiceHisId(IdUtils.snowLId());
        plClientServiceHis.setCreateBy(user.getUsername());
        return toAjax(plClientServiceHisService.insertPlClientServiceHis(plClientServiceHis));
    }

    /**
     * 修改客户跟进记录
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServiceHis", value = "客户跟进记录对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改客户跟进记录")
    @PreAuthorize("@ss.hasPermi('project:client:his:edit')")
    @Log(title = "客户跟进记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlClientServiceHis plClientServiceHis) {
        plClientServiceHis.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(plClientServiceHisService.updatePlClientServiceHis(plClientServiceHis));
    }

    /**
     * 删除客户跟进记录
     */
    @ApiOperation(value = "删除客户跟进记录")
    @PreAuthorize("@ss.hasPermi('project:client:his:remove')")
    @Log(title = "客户跟进记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{serviceHisIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceHisIds", value = "客户跟进记录ID[]", required = true)
    })
    public AjaxResult remove(@PathVariable Long[] serviceHisIds) {
        return toAjax(plClientServiceHisService.deletePlClientServiceHisByIds(serviceHisIds));
    }
}