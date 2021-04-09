package com.ruoyi.project.contract.domain;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同收费计划对象 pl_contract_fee_plan
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@ApiModel(description = "合同收费计划对象")
public class PlContractFeePlan extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 收费计划ID
     */
    @ApiModelProperty(value = "收费计划ID")
    private Long planId;
    /***计划收费所在年份*/
    private int planTime;
    /***实收费用*/
    private Long collectedFee;

    /**
     * 合同ID
     */
    @ApiModelProperty(value = "合同ID")
    @Excel(name = "合同ID")
    private String contractId;

    /**
     * 计划说明
     */
    @ApiModelProperty(value = "计划说明")
    @Excel(name = "计划说明")
    private String planDesc;

    /**
     * 计划收费(万元)
     */
    @ApiModelProperty(value = "计划收费(万元)")
    @Excel(name = "计划收费(万元)")
    private BigDecimal planFee;

    /**
     * 计划付费时间
     */
    @ApiModelProperty(value = "计划付费时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划付费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planPaymentTime;

    /**
     * 提前几天提醒
     */
    @ApiModelProperty(value = "提前几天提醒")
    @Excel(name = "提前几天提醒")
    private Integer remindDaysAdvance;

    /**
     * 提醒日期
     */
    @ApiModelProperty(value = "提醒日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提醒日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reminderDate;

    /**
     * 提醒人用户ID
     */
    @ApiModelProperty(value = "提醒人用户ID")
    @Excel(name = "提醒人用户ID")
    private Long reminder;

    /**
     * 提醒人名称
     */
    @ApiModelProperty(value = "提醒人名称")
    @Excel(name = "提醒人名称")
    private String reminderName;

    /**
     * 是否已发送提醒：是，否
     */
    @ApiModelProperty(value = "是否已发送提醒：是，否")
    @Excel(name = "是否已发送提醒：是，否")
    private String sentReminder;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 是否收款完成(1是 / 0否)
     */
    @ApiModelProperty(value = "是否收款完成(1是 / 0否)")
    @Excel(name = "是否收款完成(1是 / 0否)")
    private String completeStatus;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /**
     * 创建者名称
     */
    @ApiModelProperty(value = "创建者名称")
    @Excel(name = "创建者名称")
    private String createByName;

    public int getPlanTime() {
        return planTime;
    }

    public void setPlanTime(int planTime) {
        this.planTime = planTime;
    }

    public int getPlanYear() {
        return planTime;
    }

    public void setPlanYear(int planYear) {
        this.planTime = planYear;
    }

    public Long getCollectedFee() {
        return collectedFee;
    }

    public void setCollectedFee(Long collectedFee) {
        this.collectedFee = collectedFee;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanFee(BigDecimal planFee) {
        this.planFee = planFee;
    }

    public BigDecimal getPlanFee() {
        return planFee;
    }

    public void setPlanPaymentTime(Date planPaymentTime) {
        this.planPaymentTime = planPaymentTime;
    }

    public Date getPlanPaymentTime() {
        return planPaymentTime;
    }

    public void setRemindDaysAdvance(Integer remindDaysAdvance) {
        this.remindDaysAdvance = remindDaysAdvance;
    }

    public Integer getRemindDaysAdvance() {
        return remindDaysAdvance;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminder(Long reminder) {
        this.reminder = reminder;
    }

    public Long getReminder() {
        return reminder;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public String getReminderName() {
        return reminderName;
    }

    public void setSentReminder(String sentReminder) {
        this.sentReminder = sentReminder;
    }

    public String getSentReminder() {
        return sentReminder;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCreateByName() {
        return createByName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("planId", getPlanId())
                .append("contractId", getContractId())
                .append("planDesc", getPlanDesc())
                .append("planFee", getPlanFee())
                .append("planPaymentTime", getPlanPaymentTime())
                .append("remindDaysAdvance", getRemindDaysAdvance())
                .append("reminderDate", getReminderDate())
                .append("reminder", getReminder())
                .append("reminderName", getReminderName())
                .append("sentReminder", getSentReminder())
                .append("status", getStatus())
                .append("completeStatus", getCompleteStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createByName", getCreateByName())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
