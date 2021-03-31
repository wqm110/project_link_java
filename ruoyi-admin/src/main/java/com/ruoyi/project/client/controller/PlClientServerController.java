package com.ruoyi.project.client.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.client.domain.PlClient;
import com.ruoyi.project.client.domain.PlClientServer;
import com.ruoyi.project.client.domain.Sales;
import com.ruoyi.project.client.service.IPlClientServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户-业务员服务关系Controller
 *
 * @author ruoyi
 * @date 2021-03-30
 */
//@Api(tags = "A客户-业务员服务关系")
@RestController
@RequestMapping("/project/client_server")
public class PlClientServerController extends BaseController {
    @Autowired
    private IPlClientServerService plClientServerService;

    /**
     * 批量删除、新增客户关系
     */
    @PostMapping("/saveOrUpateInBatch/")
    @ApiOperation(value = "给业务员批量添加跟踪客户", response = AjaxResult.class)
    @ApiImplicitParam(name = "sales", value = "客户-业务业务员管理列表")
    public AjaxResult UpdateInBatch(@RequestBody Sales sales) {
        Long userId = sales.getUserId();
        ArrayList<PlClient> clients = sales.getClients();
        clients.forEach(x -> x.setUserId(userId));
        plClientServerService.deletePlClientServerByUserId(userId);
        clients.forEach(x -> {
            PlClientServer server = new PlClientServer();
            server.setClientId(x.getClientId());
            server.setUserId(userId);
            server.setDelFlag("0");
            server.setCreateTime(DateUtils.getNowDate());
            server.setCreateBy(SecurityUtils.getUsername());
            try {
                plClientServerService.insertPlClientServer(server);
                System.out.println("添加n = ");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("修改= ");
                plClientServerService.updatePlClientServer(server);
            }
        });
        return AjaxResult.success("批量更新客户关系成功");
    }

    /**
     * 查询客户-业务员服务关系列表
     */
    @PreAuthorize("@ss.hasPermi('project:client_server:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询客户-业务员服务关系列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServer", value = "客户-业务员服务关系查询对象", required = false, paramType = "query")
    })
    public TableDataInfo list(PlClientServer plClientServer) {
        startPage();
        List<PlClientServer> list = plClientServerService.selectPlClientServerList(plClientServer);
        return getDataTable(list);
    }

    /**
     * 导出客户-业务员服务关系列表
     */
    @ApiOperation(value = "导出客户-业务员服务关系列表")
    @PreAuthorize("@ss.hasPermi('project:client_server:export')")
    @Log(title = "客户-业务员服务关系", businessType = BusinessType.EXPORT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServer", value = "客户-业务员服务关系查询对象", required = false, paramType = "query")
    })
    @GetMapping("/export")
    public AjaxResult export(PlClientServer plClientServer) {
        List<PlClientServer> list = plClientServerService.selectPlClientServerList(plClientServer);
        ExcelUtil<PlClientServer> util = new ExcelUtil<PlClientServer>(PlClientServer.class);
        return util.exportExcel(list, "client_server");
    }

    /**
     * 获取客户-业务员服务关系详细信息
     */
    @ApiOperation(value = "获取客户-业务员服务关系详细信息")
    @PreAuthorize("@ss.hasPermi('project:client_server:query')")
    @GetMapping(value = "/{clientId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientId", value = "客户-业务员服务关系ID", required = true, paramType = "query")
    })
    public AjaxResult getInfo(@PathVariable("clientId") String clientId) {
        return AjaxResult.success(plClientServerService.selectPlClientServerById(clientId));
    }

    /**
     * 新增客户-业务员服务关系
     */
    @ApiOperation(value = "新增客户-业务员服务关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServer", value = "客户-业务员服务关系对象", required = true,
                    paramType = "body", example = "{ \"clients\": [{\"clientId\":\"6\"},{\"clientId\":\"8\"}],\"userId\": 2}")
    })
    @PreAuthorize("@ss.hasPermi('project:client_server:add')")
    @Log(title = "客户-业务员服务关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlClientServer plClientServer) {
        if (null == plClientServer.getUserId()) {
            plClientServer.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        return toAjax(plClientServerService.insertPlClientServer(plClientServer));
    }

    /**
     * 修改客户-业务员服务关系
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plClientServer", value = "客户-业务员服务关系对象", required = true, paramType = "body")
    })
    @ApiOperation(value = "修改客户-业务员服务关系")
    @PreAuthorize("@ss.hasPermi('project:client_server:edit')")
    @Log(title = "客户-业务员服务关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlClientServer plClientServer) {
        return toAjax(plClientServerService.updatePlClientServer(plClientServer));
    }

    /**
     * 删除客户-业务员服务关系
     */
    @ApiOperation(value = "删除客户-业务员服务关系")
    @PreAuthorize("@ss.hasPermi('project:client_server:remove')")
    @Log(title = "客户-业务员服务关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{clientIds}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientIds", value = "客户-业务员服务关系ID[]", required = true)
    })
    public AjaxResult remove(@PathVariable String[] clientIds) {
        return toAjax(plClientServerService.deletePlClientServerByIds(clientIds));
    }
}