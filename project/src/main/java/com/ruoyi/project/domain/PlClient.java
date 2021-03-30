package com.ruoyi.project.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户信息对象 pl_client
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@ApiModel(description = "客户信息对象")
public class PlClient extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String clientId;

    /**
     * 业务员编号
     */
    @ApiModelProperty(value = "业务员编号")
    @Excel(name = "业务员编号")
    private String userId;

    /**
     * 客户编号
     */
    @ApiModelProperty(value = "客户编号")
    @Excel(name = "客户编号")
    private String clientNumber;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    @Excel(name = "客户名称")
    private String clientName;

    /**
     * 客户行业类别
     */
    @ApiModelProperty(value = "客户行业类别")
    @Excel(name = "客户行业类别")
    private String industry;

    /**
     * 客户单位性质
     */
    @ApiModelProperty(value = "客户单位性质")
    @Excel(name = "客户单位性质")
    private String unitProperty;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @Excel(name = "联系人")
    private String contacts;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String contactNumber;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    @Excel(name = "省份")
    private String province;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    @Excel(name = "邮编")
    private String zipCode;

    /**
     * 开户银行
     */
    @ApiModelProperty(value = "开户银行")
    @Excel(name = "开户银行")
    private String bankName;

    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号")
    @Excel(name = "开户账号")
    private String accountNumber;

    /**
     * 办公电话
     */
    @ApiModelProperty(value = "办公电话")
    @Excel(name = "办公电话")
    private String businessPhone;

    /**
     * 公司地址
     */
    @ApiModelProperty(value = "公司地址")
    @Excel(name = "公司地址")
    private String companyAddress;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;
    /**
     * 重点客户标志（0代表普通客户 1代表重点客户）
     */
    @ApiModelProperty(value = "删除标志（0代表普通客户 1代表重点客户）")
    private String vipFlag;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    public void setUnitProperty(String unitProperty) {
        this.unitProperty = unitProperty;
    }

    public String getUnitProperty() {
        return unitProperty;
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

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("clientId", getClientId())
                .append("userId", getUserId())
                .append("clientNumber", getClientNumber())
                .append("clientName", getClientName())
                .append("industry", getIndustry())
                .append("unitProperty", getUnitProperty())
                .append("contacts", getContacts())
                .append("contactNumber", getContactNumber())
                .append("province", getProvince())
                .append("zipCode", getZipCode())
                .append("bankName", getBankName())
                .append("accountNumber", getAccountNumber())
                .append("businessPhone", getBusinessPhone())
                .append("companyAddress", getCompanyAddress())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
