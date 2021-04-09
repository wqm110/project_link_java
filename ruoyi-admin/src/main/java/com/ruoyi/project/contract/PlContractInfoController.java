package com.ruoyi.project.contract;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 合同信息Controller
 *
 * @author ruoyi
 * @date 2021-04-03
 */
@Api(tags = "A合同信息")
@RestController
@RequestMapping("/project/contractinfo")
public class PlContractInfoController extends BaseController {
    private IPlContractInfoService plContractInfoService;
    private final String FUNCTION_KEY = "contract";//区分附件的部分

    @Autowired
    public PlContractInfoController(IPlContractInfoService iPlContractInfoService) {
        this.plContractInfoService = iPlContractInfoService;
    }

    /**
     * 合同首页汇总信息（文字）
     */
    @GetMapping("/getSummary/data")
    @ApiOperation("合同首页汇总信息（文字_本年）")
    public AjaxResult getSummary(@RequestParam("startYear") String startYear, @RequestParam(value = "endYear", required = false) String endYear) {
        Map<String, Object> summary = plContractInfoService.getSummary(startYear, endYear);
        return AjaxResult.success(summary);
    }

    /**
     * 合同首页汇总信息（图表-合同-年）
     */
    @GetMapping("/getSummary/charts/contract/year")
    @ApiOperation("合同首页汇总信息（图表-合同-年）")
    public AjaxResult getSummaryChartsContractYear() {
        int year = DateUtil.year(new Date());
        int syear = year - 4;
        Map<String, Object> summary = plContractInfoService.getSummaryChartsContractYear(syear, year);
        return AjaxResult.success(summary);
    }

    /**
     * 合同首页汇总信息（图表-合同-月）
     */
    @GetMapping("/getSummary/charts/contract/mouth")
    @ApiOperation("合同首页汇总信息（图表-合同-月）")
    public AjaxResult getSummaryChartsContractMouth() {
        int year = DateUtil.year(new Date());
        Map<String, String> summary = plContractInfoService.getSummaryChartsContractMouth(year);
        return AjaxResult.success(summary);
    }

    /**
     * 合同首页汇总信息（图表-合同-季度）
     */
    @GetMapping("/getSummary/charts/contract/quarter")
    @ApiOperation("合同首页汇总信息（图表-合同-季度）")
    public AjaxResult getSummaryChartsContractQuarter() {
        int year = DateUtil.year(new Date());
        int syear = year - 4;
        Map<String, String> summary = plContractInfoService.getSummaryChartsContractQuerter(year);
        return AjaxResult.success(summary);
    }

    /**
     * 合同首页汇总信息（图表-实收-年）
     */
    @GetMapping("/getSummary/charts/collect/year")
    @ApiOperation("合同首页汇总信息（图表-实收-年）")
    public AjaxResult getSummaryChartsFeeCollectedYear() {
        int year = DateUtil.year(new Date());
        int syear = year - 4;
        Map<String, Object> summary = plContractInfoService.getSummaryChartsFeeCollectedYear(syear, year);
        return AjaxResult.success(summary);
    }

    /**
     * 合同首页汇总信息（图表-实收-月）
     */
    @GetMapping("/getSummary/charts/collect/month")
    @ApiOperation("合同首页汇总信息（图表-实收-月）")
    public AjaxResult getSummaryChartsFeeCollectedMonth() {
        int year = DateUtil.year(new Date());
        Map<String, Object> summary = plContractInfoService.getSummaryChartsFeeCollectedMonth(year);
        return AjaxResult.success(summary);
    }

    /**
     * 合同首页汇总信息（图表-实收-季）
     */
    @GetMapping("/getSummary/charts/collect/quarter")
    @ApiOperation("合同首页汇总信息（图表-实收-季）")
    public AjaxResult getSummaryChartsFeeCollectedQuarter() {
        int year = DateUtil.year(new Date());
        Map<String, Object> summary = plContractInfoService.getSummaryChartsFeeCollectedQuarter(year);
        return AjaxResult.success(summary);
    }

    /**
     * 我负责的合同
     */
    @ApiOperation(value = "我负责的合同")
    @GetMapping("/my/duty")
    @ApiImplicitParam(value = "我负责的合同的查询对象", required = false, dataTypeClass = PlContractInfo.class)
    public TableDataInfo myContractManaged(PlContractInfo info) {
        //这个是我登记的
        //TODO 和项目负责人有关
        return getDataTable(new ArrayList<>());
    }

    /**
     * 我创建的合同
     */
    @ApiOperation(value = "我创建的合同")
    @GetMapping("/my/create")
    public TableDataInfo myContractCreated(PlContractInfo info) {
        //这个是我登记的
        info.setAgent(SecurityUtils.getUsername());
        startPage();
        List<PlContractInfo> plContractInfos = plContractInfoService.selectPlContractInfoList(info);
        return getDataTable(plContractInfos);
    }

    /**
     * 查询合同信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:contractinfo:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询合同信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plContractInfo", value = "合同信息查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlContractInfo plContractInfo) {
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
    public AjaxResult export(PlContractInfo plContractInfo) {
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
    public AjaxResult getInfo(@PathVariable("contractId") Long contractId) {
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
    public AjaxResult add(@RequestBody PlContractInfo plContractInfo) {
        //设置id
        plContractInfo.setContractId(IdUtils.snowLId());
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //设置创建人
        plContractInfo.setCreateBy(user.getUserName());
        //设置代办人
        plContractInfo.setAgent(user.getUserId().toString());
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
    public AjaxResult edit(@RequestBody PlContractInfo plContractInfo) {
        plContractInfo.setUpdateTime(DateUtils.getNowDate());
        plContractInfo.setUpdateBy(SecurityUtils.getUsername());
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
    public AjaxResult remove(@PathVariable Long[] contractIds) {
        return toAjax(plContractInfoService.deletePlContractInfoByIds(contractIds));
    }
}
