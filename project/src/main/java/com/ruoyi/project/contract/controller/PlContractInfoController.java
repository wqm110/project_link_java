package com.ruoyi.project.contract.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.contract.domain.PlContractInfo;
import com.ruoyi.project.contract.service.IPlContractInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合同信息Controller
 * 
 * @author ruoyi
 * @date 2021-04-03
 */
@Api(tags = "A合同信息")
@RestController
@RequestMapping("/project/contractinfo")
public class PlContractInfoController extends BaseController
{
    @Autowired
    private IPlContractInfoService plContractInfoService;

    /**
     * 查询合同信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:contractinfo:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询合同信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractInfo", value = "合同信息查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlContractInfo plContractInfo)
    {
        startPage();
        List<PlContractInfo> list = plContractInfoService.selectPlContractInfoList(plContractInfo);
        return getDataTable(list);
    }

    /**
     * 导出合同信息列表
     */
    @ApiOperation(value = "导出合同信息列表")
    @PreAuthorize("@ss.hasPermi('project:contractinfo:export')")
    @Log(title = "合同信息", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractInfo", value = "合同信息查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlContractInfo plContractInfo)
    {
        List<PlContractInfo> list = plContractInfoService.selectPlContractInfoList(plContractInfo);
        ExcelUtil<PlContractInfo> util = new ExcelUtil<PlContractInfo>(PlContractInfo.class);
        return util.exportExcel(list, "contractinfo");
    }

    /**
     * 获取合同信息详细信息
     */
    @ApiOperation(value = "获取合同信息详细信息")
    @PreAuthorize("@ss.hasPermi('project:contractinfo:query')")
    @GetMapping(value = "/{contractId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractId", value = "合同信息ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("contractId") Long contractId)
    {
        return AjaxResult.success(plContractInfoService.selectPlContractInfoById(contractId));
    }

    /**
     * 新增合同信息
     */
    @ApiOperation(value = "新增合同信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractInfo", value = "合同信息对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:contractinfo:add')")
    @Log(title = "合同信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlContractInfo plContractInfo)
    {
        return toAjax(plContractInfoService.insertPlContractInfo(plContractInfo));
    }

    /**
     * 修改合同信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractInfo", value = "合同信息对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改合同信息")
    @PreAuthorize("@ss.hasPermi('project:contractinfo:edit')")
    @Log(title = "合同信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlContractInfo plContractInfo)
    {
        return toAjax(plContractInfoService.updatePlContractInfo(plContractInfo));
    }

    /**
     * 删除合同信息
     */
    @ApiOperation(value = "删除合同信息")
    @PreAuthorize("@ss.hasPermi('project:contractinfo:remove')")
    @Log(title = "合同信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contractIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractIds", value = "合同信息ID[]", required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable Long[] contractIds)
    {
        return toAjax(plContractInfoService.deletePlContractInfoByIds(contractIds));
    }
}
