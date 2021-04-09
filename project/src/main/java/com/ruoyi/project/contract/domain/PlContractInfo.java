package com.ruoyi.project.contract.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同信息对象 pl_contract_info
 *
 * @author ruoyi
 * @date 2021-04-03
 */
@ApiModel(description = "合同信息对象")
public class PlContractInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 签订年份
     */
//    @ApiModelProperty(value = "签订年份-查询用")
    private String sy;
    /**
     * 签订月份
     */
//    @ApiModelProperty(value = "签订年份-查询用")
    private String sm;

    /**
     * 合同ID
     */
    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    @Excel(name = "合同名称")
    private String contractName;

    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    @Excel(name = "合同编号")
    private String contractNumber;

    /**
     * 承办部门
     */
    @ApiModelProperty(value = "承办部门")
    @Excel(name = "承办部门")
    private Long deptId;

    /**
     * 关联项目
     */
    @ApiModelProperty(value = "关联项目")
    @Excel(name = "关联项目")
    private Long projectId;

    /**
     * 合同状态(0-未签订 1-已签订 2-已完成)
     */
    @ApiModelProperty(value = "合同状态(0-未签订 1-已签订 2-已完成)")
    @Excel(name = "合同状态(0-未签订 1-已签订 2-已完成)")
    private String contractStatus;

    /**
     * 客户
     */
    @ApiModelProperty(value = "客户")
    @Excel(name = "客户")
    private Long clientId;

    /**
     * 生效日期
     */
    @ApiModelProperty(value = "生效日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /**
     * 签订日期
     */
    @ApiModelProperty(value = "签订日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签订日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signingDate;

    /**
     * 合同金额(万元)
     */
    @ApiModelProperty(value = "合同金额(万元)")
    @Excel(name = "合同金额(万元)")
    private BigDecimal contractAmount;

    /**
     * 完成日期
     */
    @ApiModelProperty(value = "完成日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completionDate;

    /**
     * 业主联系人
     */
    @ApiModelProperty(value = "业主联系人")
    @Excel(name = "业主联系人")
    private String contact;

    /**
     * 费用构成
     */
    @ApiModelProperty(value = "费用构成")
    @Excel(name = "费用构成")
    private String costStructure;

    /**
     * 业主联系方式
     */
    @ApiModelProperty(value = "业主联系方式")
    @Excel(name = "业主联系方式")
    private String ownerContact;

    /**
     * 院法人代表
     */
    @ApiModelProperty(value = "院法人代表")
    @Excel(name = "院法人代表")
    private String legalRepresentative;

    /**
     * 业主法人代表
     */
    @ApiModelProperty(value = "业主法人代表")
    @Excel(name = "业主法人代表")
    private String ownerLegalRepresentative;

    /**
     * 业主项目负责人姓名
     */
    @ApiModelProperty(value = "业主项目负责人姓名")
    @Excel(name = "业主项目负责人姓名")
    private String ownerProjectManager;

    /**
     * 项目负责人ID
     */
    @ApiModelProperty(value = "项目负责人ID")
    @Excel(name = "项目负责人ID")
    private Long userId;

    /**
     * 项目负责人名称
     */
    @ApiModelProperty(value = "项目负责人名称")
    @Excel(name = "项目负责人名称")
    private String projectManagerName;

    /**
     * 项目负责人电话
     */
    @ApiModelProperty(value = "项目负责人电话")
    @Excel(name = "项目负责人电话")
    private String projectManagerPhone;

    /**
     * 签订地点
     */
    @ApiModelProperty(value = "签订地点")
    @Excel(name = "签订地点")
    private String placeOfSigning;

    /**
     * 商务条款
     */
    @ApiModelProperty(value = "商务条款")
    @Excel(name = "商务条款")
    private String businessTerms;

    /**
     * 技术条款
     */
    @ApiModelProperty(value = "技术条款")
    @Excel(name = "技术条款")
    private String technicalTerms;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业")
    @Excel(name = "专业")
    private String specialty;

    /**
     * 经办人id
     */
    @ApiModelProperty(value = "经办人id")
    @Excel(name = "经办人id")
    private String agent;

    /**
     * 经办人姓名
     */
    @ApiModelProperty(value = "经办人姓名")
    @Excel(name = "经办人姓名")
    private String operatorName;

    /**
     * 经办人电话
     */
    @ApiModelProperty(value = "经办人电话")
    @Excel(name = "经办人电话")
    private String operatorPhone;

    /**
     * 项目规模
     */
    @ApiModelProperty(value = "项目规模")
    @Excel(name = "项目规模")
    private String projectScale;

    /**
     * 流程实例ID
     */
    @ApiModelProperty(value = "流程实例ID")
    @Excel(name = "流程实例ID")
    private String instanceId;

    /**
     * 申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)
     */
    @ApiModelProperty(value = "申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)")
    @Excel(name = "申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)")
    private String applyStatus;

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
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /**
     * 承办部门
     */
    @ApiModelProperty(value = "承办部门")
    private SysDept dept;
    /**
     * 项目负责人
     */
    @ApiModelProperty(value = "项目负责人")
    private SysUser projectManager;

    public String getSy() {
        return sy;
    }

    public void setSy(String sy) {
        this.sy = sy;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public SysUser getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(SysUser projectManager) {
        this.projectManager = projectManager;
    }

    public SysDept getDept() {
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }


    public void setCostStructure(String costStructure) {
        this.costStructure = costStructure;
    }

    public String getCostStructure() {
        return costStructure;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setOwnerLegalRepresentative(String ownerLegalRepresentative) {
        this.ownerLegalRepresentative = ownerLegalRepresentative;
    }

    public String getOwnerLegalRepresentative() {
        return ownerLegalRepresentative;
    }

    public void setOwnerProjectManager(String ownerProjectManager) {
        this.ownerProjectManager = ownerProjectManager;
    }

    public String getOwnerProjectManager() {
        return ownerProjectManager;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerPhone(String projectManagerPhone) {
        this.projectManagerPhone = projectManagerPhone;
    }

    public String getProjectManagerPhone() {
        return projectManagerPhone;
    }

    public void setPlaceOfSigning(String placeOfSigning) {
        this.placeOfSigning = placeOfSigning;
    }

    public String getPlaceOfSigning() {
        return placeOfSigning;
    }

    public void setBusinessTerms(String businessTerms) {
        this.businessTerms = businessTerms;
    }

    public String getBusinessTerms() {
        return businessTerms;
    }

    public void setTechnicalTerms(String technicalTerms) {
        this.technicalTerms = technicalTerms;
    }

    public String getTechnicalTerms() {
        return technicalTerms;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setProjectScale(String projectScale) {
        this.projectScale = projectScale;
    }

    public String getProjectScale() {
        return projectScale;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus() {
        return applyStatus;
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

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("contractId", getContractId())
                .append("contractName", getContractName())
                .append("contractNumber", getContractNumber())
                .append("deptId", getDeptId())
                .append("projectId", getProjectId())
                .append("contractStatus", getContractStatus())
                .append("clientId", getClientId())
                .append("effectiveDate", getEffectiveDate())
                .append("signingDate", getSigningDate())
                .append("contractAmount", getContractAmount())
                .append("completionDate", getCompletionDate())
                .append("contact", getContact())
                .append("costStructure", getCostStructure())
                .append("ownerContact", getOwnerContact())
                .append("legalRepresentative", getLegalRepresentative())
                .append("ownerLegalRepresentative", getOwnerLegalRepresentative())
                .append("ownerProjectManager", getOwnerProjectManager())
                .append("userId", getUserId())
                .append("projectManagerName", getProjectManagerName())
                .append("projectManagerPhone", getProjectManagerPhone())
                .append("placeOfSigning", getPlaceOfSigning())
                .append("businessTerms", getBusinessTerms())
                .append("technicalTerms", getTechnicalTerms())
                .append("specialty", getSpecialty())
                .append("agent", getAgent())
                .append("operatorName", getOperatorName())
                .append("operatorPhone", getOperatorPhone())
                .append("projectScale", getProjectScale())
                .append("instanceId", getInstanceId())
                .append("applyStatus", getApplyStatus())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("applyTime", getApplyTime())
                .toString();
    }
}
