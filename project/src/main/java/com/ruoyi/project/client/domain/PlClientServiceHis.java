package com.ruoyi.project.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;

/**
 * 客户跟进记录对象 pl_client_service_his
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@ApiModel(description = "客户跟进记录对象")
public class PlClientServiceHis extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long serviceHisId;

    /**
     * 关联的客户主键
     */
    @ApiModelProperty(value = "关联的客户主键")
    @Excel(name = "关联的客户主键")
    private Long clientId;

    /**
     * 业务员编号
     */
    @ApiModelProperty(value = "业务员编号")
    @Excel(name = "业务员编号")
    private Long userId;

    /**
     * 删除标志（0正常 2 删除 ）
     */
    @ApiModelProperty(value = "删除标志（0正常 2 删除 ）")
    private String delFlag="0";

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    @Excel(name = "客户名称")
    private String clientName;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @Excel(name = "联系人")
    private String contacts;

    /**
     * 联系人电话
     */
    @ApiModelProperty(value = "联系人电话")
    @Excel(name = "联系人电话")
    private String contactNumber;

    /**
     * 沟通详情
     */
    @ApiModelProperty(value = "沟通详情")
    @Excel(name = "沟通详情")
    private String detail;
    /**
     * 跟进时间
     */
    @ApiModelProperty(value = "跟进时间")
    @Excel(name = "跟进时间")
    private Date serviceTime;



    /**
     * 开始时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonIgnore
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Long getServiceHisId() {
        return serviceHisId;
    }

    public void setServiceHisId(Long serviceHisId) {
        this.serviceHisId = serviceHisId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("serviceHisId", getServiceHisId())
                .append("clientId", getClientId())
                .append("userId", getUserId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .append("clientName", getClientName())
                .append("contacts", getContacts())
                .append("contactNumber", getContactNumber())
                .append("detail", getDetail())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}