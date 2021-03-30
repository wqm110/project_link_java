package com.ruoyi.project.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.domain.PlClient;
import com.ruoyi.project.service.IPlClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户信息Controller
 * 
 * @author ruoyi
 * @date 2021-03-30
 */
@Api(tags = "A客户信息")
@RestController
@RequestMapping("/project/client")
public class PlClientController extends BaseController
{
    @Autowired
    private IPlClientService plClientService;

    /**
     * 查询客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:client:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询客户信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClient", value = "客户信息查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlClient plClient)
    {
        startPage();
        List<PlClient> list = plClientService.selectPlClientList(plClient);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @ApiOperation(value = "导出客户信息列表")
    @PreAuthorize("@ss.hasPermi('project:client:export')")
    @Log(title = "客户信息", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClient", value = "客户信息查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlClient plClient)
    {
        List<PlClient> list = plClientService.selectPlClientList(plClient);
        ExcelUtil<PlClient> util = new ExcelUtil<PlClient>(PlClient.class);
        return util.exportExcel(list, "client");
    }

    /**
     * 获取客户信息详细信息
     */
    @ApiOperation(value = "获取客户信息详细信息")
    @PreAuthorize("@ss.hasPermi('project:client:query')")
    @GetMapping(value = "/{clientId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientId", value = "客户信息ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("clientId") String clientId)
    {
        return AjaxResult.success(plClientService.selectPlClientById(clientId));
    }

    /**
     * 新增客户信息
     */
    @ApiOperation(value = "新增客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClient", value = "客户信息对象", required = true, paramType = "body")
    })
    @PreAuthorize("@ss.hasPermi('project:client:add')")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlClient plClient)
    {
        return toAjax(plClientService.insertPlClient(plClient));
    }

    /**
     * 修改客户信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClient", value = "客户信息对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改客户信息")
    @PreAuthorize("@ss.hasPermi('project:client:edit')")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlClient plClient)
    {
        return toAjax(plClientService.updatePlClient(plClient));
    }

    /**
     * 删除客户信息
     */
    @ApiOperation(value = "删除客户信息")
    @PreAuthorize("@ss.hasPermi('project:client:remove')")
    @Log(title = "客户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clientIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientIds", value = "客户信息ID[]", required = true, paramType = "body")
    })
    public AjaxResult remove(@PathVariable String[] clientIds)
    {
        return toAjax(plClientService.deletePlClientByIds(clientIds));
    }
}
