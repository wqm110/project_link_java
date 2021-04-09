package com.ruoyi.project.contract;

import java.util.List;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.project.contract.service.IPlContractFeePlanService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.ruoyi.project.contract.domain.PlContractFeePlan;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同收费计划Controller
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@Api(tags = "A合同收费计划")
@RestController
@RequestMapping("/project/feeplan")
public class PlContractFeePlanController extends BaseController {
    private IPlContractFeePlanService plContractFeePlanService;

    @Autowired
    PlContractFeePlanController(IPlContractFeePlanService plContractFeePlanService) {
        this.plContractFeePlanService = plContractFeePlanService;
    }

    /**
     * 根据合同id查询计划列表
     */
    @ApiOperation(value = "根据合同id查询计划列表")
    @ApiImplicitParam(value = "合同编号", required = true)
    public TableDataInfo getContractPlans(String contractId) {
        List<PlContractFeePlan> list = plContractFeePlanService.getContractPlansByFather(contractId);
        return getDataTable(list);
    }

    /**
     * 查询合同收费计划列表
     */
    @PreAuthorize("@ss.hasPermi('project:feeplan:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询合同收费计划列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractFeePlan", value = "合同收费计划查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlContractFeePlan plContractFeePlan) {
        startPage();
        List<PlContractFeePlan> list = plContractFeePlanService.selectPlContractFeePlanList(plContractFeePlan);
        return getDataTable(list);
    }

    /**
     * 导出合同收费计划列表
     */
    @ApiOperation(value = "导出合同收费计划列表")
    @PreAuthorize("@ss.hasPermi('project:feeplan:export')")
    @Log(title = "合同收费计划", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractFeePlan", value = "合同收费计划查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlContractFeePlan plContractFeePlan) {
        List<PlContractFeePlan> list = plContractFeePlanService.selectPlContractFeePlanList(plContractFeePlan);
        ExcelUtil<PlContractFeePlan> util = new ExcelUtil<PlContractFeePlan>(PlContractFeePlan.class);
        return util.exportExcel(list, "feeplan");
    }

    /**
     * 获取合同收费计划详细信息
     */
    @ApiOperation(value = "获取合同收费计划详细信息")
    @PreAuthorize("@ss.hasPermi('project:feeplan:query')")
    @GetMapping(value = "/{planId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "合同收费计划ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("planId") Long planId) {
        return AjaxResult.success(plContractFeePlanService.selectPlContractFeePlanById(planId));
    }

    /**
     * 新增合同收费计划
     */
    @ApiOperation(value = "新增合同收费计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractFeePlan", value = "合同收费计划对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:feeplan:add')")
    @Log(title = "合同收费计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlContractFeePlan plContractFeePlan) {
        plContractFeePlan.setPlanId(IdUtils.snowLId());
        return toAjax(plContractFeePlanService.insertPlContractFeePlan(plContractFeePlan));
    }

    /**
     * 修改合同收费计划
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractFeePlan", value = "合同收费计划对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改合同收费计划")
    @PreAuthorize("@ss.hasPermi('project:feeplan:edit')")
    @Log(title = "合同收费计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlContractFeePlan plContractFeePlan) {
        return toAjax(plContractFeePlanService.updatePlContractFeePlan(plContractFeePlan));
    }

    /**
     * 删除合同收费计划
     */
    @ApiOperation(value = "删除合同收费计划")
    @PreAuthorize("@ss.hasPermi('project:feeplan:remove')")
    @Log(title = "合同收费计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{planIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planIds", value = "合同收费计划ID[]", required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable String[] planIds) {
        return toAjax(plContractFeePlanService.deletePlContractFeePlanByIds(planIds));
    }
}
