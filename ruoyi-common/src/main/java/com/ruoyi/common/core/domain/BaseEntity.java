package com.ruoyi.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.TransformUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity基类
 *
 * @author ruoyi
 */
@ApiModel(description = "基类")
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object id;


    /**
     * 搜索值
     */
    @ApiModelProperty(value = "搜索值")
    private String searchValue;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private Map<String, Object> params;

    /**
     * 后台到前台 long-> 字符串
     */
    // 数据往前台传, 为解决前台long长度过长导致的精度缺失
    public static List idsLong2String(List<? extends BaseEntity> li) {
        if (li == null || li.size() == 0) {
            return li;
        }
        for (BaseEntity bean : li) {
            bean.setId(bean.getId().toString());
        }
        return li;
    }

    /**
     * 前台到后台 字符串->long
     */
    // 数据由前台往底层传, String转Long以匹配底层long类型主键
    public static List idsString2Long(List<? extends BaseEntity> li) {
        if (li == null || li.size() == 0) {
            return li;
        }
        for (BaseEntity bean : li) {
            bean.setId(TransformUtil.getLong((String) bean.getId()));
        }
        return li;
    }


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
