package com.ruoyi.project.client.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.bidinfo.domain.PlBidInfo;
import com.ruoyi.project.bidinfo.service.IPlBidInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投标信息申请Controller
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@Api(tags = "A投标信息申请")
@RestController
@RequestMapping("/project/bidinfo")
public class PlBidInfoController extends BaseController {
    @Autowired
    private IPlBidInfoService plBidInfoService;

    /**
     * 查询已录入的招标单位
     */
    @ApiOperation(value = "查询已录入的招标单位(下拉)", response = TableDataInfo.class)
    @GetMapping("/listBiddingUnits")
    public TableDataInfo listBiddingUnits() {
        List<PlBidInfo> list = plBidInfoService.slectBiddingUnits();
        return getDataTable(list);
    }

    /**
     * 查寻已录入的投标地点(下拉)
     */
    @ApiOperation(value = "查寻已录入的投标地点", response = AjaxResult.class)
    @GetMapping("/listBidPlaces")
    public AjaxResult listBidPlaces() {
        List<PlBidInfo> list = plBidInfoService.selectBidPlaces();
        return AjaxResult.success("200", list);
    }

    /**
     * 查寻已录入的经办人（下拉）
     */
    @ApiOperation(value = "查寻已录入的经办人（下拉", response = AjaxResult.class)
    @ApiImplicitParam(name = "flag", value = "查寻（true只查本人false查询全部）相关经办人", required = true)
    @GetMapping("/listAgents/{flag}")
    public AjaxResult listAgents(@PathVariable Boolean flag) {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<PlBidInfo> list = null;
        if (flag) {
            list = plBidInfoService.selectAgents(userId);
        } else {
            userId = null;
            list = plBidInfoService.selectAgents(userId);
        }
        return AjaxResult.success("查寻经办人成功", list);
    }

    /**
     * 列出全部登记人
     */
    @GetMapping("/listCreaters")
    @ApiOperation(value = "查寻登记人", response = AjaxResult.class)
    public AjaxResult listCeaters() {
        List<PlBidInfo> list = plBidInfoService.listCeaters();
        return AjaxResult.success(list);
    }

    /**
     * 查询投标信息申请列表
     */
    @PreAuthorize("@ss.hasPermi('project:bidinfo:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询投标信息申请列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plBidInfo", value = "投标信息申请查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlBidInfo plBidInfo) {
        startPage();
        List<PlBidInfo> list = plBidInfoService.selectPlBidInfoList(plBidInfo);
        return getDataTable(list);
    }

    /**
     * 导出投标信息申请列表
     */
    @ApiOperation(value = "导出投标信息申请列表")
    @PreAuthorize("@ss.hasPermi('project:bidinfo:export')")
    @Log(title = "投标信息申请", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plBidInfo", value = "投标信息申请查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlBidInfo plBidInfo) {
        List<PlBidInfo> list = plBidInfoService.selectPlBidInfoList(plBidInfo);
        ExcelUtil<PlBidInfo> util = new ExcelUtil<PlBidInfo>(PlBidInfo.class);
        return util.exportExcel(list, "bidinfo");
    }

    /**
     * 获取投标信息申请详细信息
     */
    @ApiOperation(value = "获取投标信息申请详细信息")
    @PreAuthorize("@ss.hasPermi('project:bidinfo:query')")
    @GetMapping(value = "/{bidId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bidId", value = "投标信息申请ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("bidId") Long bidId) {
        return AjaxResult.success(plBidInfoService.selectPlBidInfoById(bidId));
    }

    /**
     * 新增投标信息申请
     */
    @ApiOperation(value = "新增投标信息申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plBidInfo", value = "投标信息申请对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:bidinfo:add')")
    @Log(title = "投标信息申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlBidInfo plBidInfo) {
        plBidInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(plBidInfoService.insertPlBidInfo(plBidInfo));
    }

    /**
     * 修改投标信息申请
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plBidInfo", value = "投标信息申请对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改投标信息申请")
    @PreAuthorize("@ss.hasPermi('project:bidinfo:edit')")
    @Log(title = "投标信息申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlBidInfo plBidInfo) {
        plBidInfo.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(plBidInfoService.updatePlBidInfo(plBidInfo));
    }

    /**
     * 删除投标信息申请
     */
    @ApiOperation(value = "删除投标信息申请")
    @PreAuthorize("@ss.hasPermi('project:bidinfo:remove')")
    @Log(title = "投标信息申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bidIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bidIds", value = "投标信息申请ID[]", required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable Long[] bidIds) {
        return toAjax(plBidInfoService.deletePlBidInfoByIds(bidIds));
    }
}
