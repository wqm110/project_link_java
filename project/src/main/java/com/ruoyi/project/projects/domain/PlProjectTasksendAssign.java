package com.ruoyi.project.projects.domain;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务下达对象 pl_project_tasksend_assign
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@ApiModel(description= "任务下达对象")
public class PlProjectTasksendAssign extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private String id;

    /** 申请人id */
    @ApiModelProperty(value = "申请人id")
    @Excel(name = "申请人id")
    private String applyUserId;

    /** 申请时间 */
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 任务书编号 */
    @ApiModelProperty(value = "任务书编号")
    @Excel(name = "任务书编号")
    private String number;

    /** 关联的项目id */
    @ApiModelProperty(value = "关联的项目id")
    @Excel(name = "关联的项目id")
    private String projectId;

    /** 设计阶段 */
    @ApiModelProperty(value = "设计阶段")
    @Excel(name = "设计阶段")
    private String designPhase;

    /** 设计周期 */
    @ApiModelProperty(value = "设计周期")
    @Excel(name = "设计周期")
    private String designCycle;

    /** 设计部门 */
    @ApiModelProperty(value = "设计部门")
    @Excel(name = "设计部门")
    private String desginDept;

    /** 审核级别 */
    @ApiModelProperty(value = "审核级别")
    @Excel(name = "审核级别")
    private String censorClass;

    /** 公司名称 */
    @ApiModelProperty(value = "公司名称")
    @Excel(name = "公司名称")
    private String constructUnitName;

    /** 建设单位 */
    @ApiModelProperty(value = "建设单位")
    @Excel(name = "建设单位")
    private String conUnit;

    /** 联系人姓名 */
    @ApiModelProperty(value = "联系人姓名")
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系电话 */
    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 公司地址 */
    @ApiModelProperty(value = "公司地址")
    @Excel(name = "公司地址")
    private String address;

    /** 公司邮箱 */
    @ApiModelProperty(value = "公司邮箱")
    @Excel(name = "公司邮箱")
    private String email;

    /** 设计要求 */
    @ApiModelProperty(value = "设计要求")
    @Excel(name = "设计要求")
    private String designRequire;

    /** 设计计划备注 */
    @ApiModelProperty(value = "设计计划备注")
    @Excel(name = "设计计划备注")
    private String designPlanRemark;

    /** 规划方案计划开始时间 */
    @ApiModelProperty(value = "规划方案计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "规划方案计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ghStartTime;

    /** 规划方案计划结束时间 */
    @ApiModelProperty(value = "规划方案计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "规划方案计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ghEndTime;

    /** 初步计划开始时间 */
    @ApiModelProperty(value = "初步计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "初步计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cbStartTime;

    /** 初步计划结束时间 */
    @ApiModelProperty(value = "初步计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "初步计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cbEndTime;

    /** 施工图计划开始时间 */
    @ApiModelProperty(value = "施工图计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "施工图计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sgtStartTime;

    /** 施工图计划结束时间 */
    @ApiModelProperty(value = "施工图计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "施工图计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sgtEndTime;

    /** 附件 */
    @ApiModelProperty(value = "附件")
    @Excel(name = "附件")
    private String attach;

    /** 日期 (不知道干啥的日期) */
    @ApiModelProperty(value = "日期 (不知道干啥的日期)")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期 (不知道干啥的日期)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tsDate;

    /** 流程实例id */
    @ApiModelProperty(value = "流程实例id")
    @Excel(name = "流程实例id")
    private String instanceId;

    /** 申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销) */
    @ApiModelProperty(value = "申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)")
    @Excel(name = "申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)")
    private String applyStatus;

    /** 删除标识 0-未删除 1-已删除 */
    @ApiModelProperty(value = "删除标识 0-未删除 1-已删除")
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setApplyUserId(String applyUserId) 
    {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserId() 
    {
        return applyUserId;
    }
    public void setApplyTime(Date applyTime) 
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() 
    {
        return applyTime;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setProjectId(String projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectId() 
    {
        return projectId;
    }
    public void setDesignPhase(String designPhase) 
    {
        this.designPhase = designPhase;
    }

    public String getDesignPhase() 
    {
        return designPhase;
    }
    public void setDesignCycle(String designCycle) 
    {
        this.designCycle = designCycle;
    }

    public String getDesignCycle() 
    {
        return designCycle;
    }
    public void setDesginDept(String desginDept) 
    {
        this.desginDept = desginDept;
    }

    public String getDesginDept() 
    {
        return desginDept;
    }
    public void setCensorClass(String censorClass) 
    {
        this.censorClass = censorClass;
    }

    public String getCensorClass() 
    {
        return censorClass;
    }
    public void setConstructUnitName(String constructUnitName) 
    {
        this.constructUnitName = constructUnitName;
    }

    public String getConstructUnitName() 
    {
        return constructUnitName;
    }
    public void setConUnit(String conUnit) 
    {
        this.conUnit = conUnit;
    }

    public String getConUnit() 
    {
        return conUnit;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setDesignRequire(String designRequire) 
    {
        this.designRequire = designRequire;
    }

    public String getDesignRequire() 
    {
        return designRequire;
    }
    public void setDesignPlanRemark(String designPlanRemark) 
    {
        this.designPlanRemark = designPlanRemark;
    }

    public String getDesignPlanRemark() 
    {
        return designPlanRemark;
    }
    public void setGhStartTime(Date ghStartTime) 
    {
        this.ghStartTime = ghStartTime;
    }

    public Date getGhStartTime() 
    {
        return ghStartTime;
    }
    public void setGhEndTime(Date ghEndTime) 
    {
        this.ghEndTime = ghEndTime;
    }

    public Date getGhEndTime() 
    {
        return ghEndTime;
    }
    public void setCbStartTime(Date cbStartTime) 
    {
        this.cbStartTime = cbStartTime;
    }

    public Date getCbStartTime() 
    {
        return cbStartTime;
    }
    public void setCbEndTime(Date cbEndTime) 
    {
        this.cbEndTime = cbEndTime;
    }

    public Date getCbEndTime() 
    {
        return cbEndTime;
    }
    public void setSgtStartTime(Date sgtStartTime) 
    {
        this.sgtStartTime = sgtStartTime;
    }

    public Date getSgtStartTime() 
    {
        return sgtStartTime;
    }
    public void setSgtEndTime(Date sgtEndTime) 
    {
        this.sgtEndTime = sgtEndTime;
    }

    public Date getSgtEndTime() 
    {
        return sgtEndTime;
    }
    public void setAttach(String attach) 
    {
        this.attach = attach;
    }

    public String getAttach() 
    {
        return attach;
    }
    public void setTsDate(Date tsDate) 
    {
        this.tsDate = tsDate;
    }

    public Date getTsDate() 
    {
        return tsDate;
    }
    public void setInstanceId(String instanceId) 
    {
        this.instanceId = instanceId;
    }

    public String getInstanceId() 
    {
        return instanceId;
    }
    public void setApplyStatus(String applyStatus) 
    {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus() 
    {
        return applyStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("applyUserId", getApplyUserId())
            .append("applyTime", getApplyTime())
            .append("number", getNumber())
            .append("projectId", getProjectId())
            .append("designPhase", getDesignPhase())
            .append("designCycle", getDesignCycle())
            .append("desginDept", getDesginDept())
            .append("censorClass", getCensorClass())
            .append("constructUnitName", getConstructUnitName())
            .append("conUnit", getConUnit())
            .append("contactName", getContactName())
            .append("contactPhone", getContactPhone())
            .append("address", getAddress())
            .append("email", getEmail())
            .append("designRequire", getDesignRequire())
            .append("designPlanRemark", getDesignPlanRemark())
            .append("ghStartTime", getGhStartTime())
            .append("ghEndTime", getGhEndTime())
            .append("cbStartTime", getCbStartTime())
            .append("cbEndTime", getCbEndTime())
            .append("sgtStartTime", getSgtStartTime())
            .append("sgtEndTime", getSgtEndTime())
            .append("attach", getAttach())
            .append("tsDate", getTsDate())
            .append("instanceId", getInstanceId())
            .append("applyStatus", getApplyStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
