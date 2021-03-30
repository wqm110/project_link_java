package com.ruoyi.web.controller.system;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.ISysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/dict/data/sp")
@Api(tags = "A专业管理")
public class SysDictTypeSpController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;

    @PreAuthorize("@ss.hasPermi('system:dict:sp:list')")
    @GetMapping("/list/{sp}")
    @ApiOperation("专业列表")
    @ApiImplicitParam(name = "sp", value = "专业类型的数据字典(查询、修改和删除和数据字典一样，dictype 固定为 = project_profession)",
            required = true, defaultValue = "project_profession", example = "project_profession")
    public TableDataInfo list(@PathVariable("sp") String sp) {
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType(sp);
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(sysDictData);
        return getDataTable(list);
    }
}
