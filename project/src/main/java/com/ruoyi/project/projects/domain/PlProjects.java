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
 * 项目基本信息对象 pl_projects
 * 
 * @author ruoyi
 * @date 2021-04-09
 */
@ApiModel(description= "项目基本信息对象")
public class PlProjects extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    /** 项目名称 */
    @ApiModelProperty(value = "项目名称")
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目编号 */
    @ApiModelProperty(value = "项目编号")
    @Excel(name = "项目编号")
    private String projectNumber;

    /** 项目负责人 */
    @ApiModelProperty(value = "项目负责人")
    @Excel(name = "项目负责人")
    private String userId;

    /** 项目负责人联系方式 */
    @ApiModelProperty(value = "项目负责人联系方式")
    @Excel(name = "项目负责人联系方式")
    private String principalContact;

    /** 项目类型 */
    @ApiModelProperty(value = "项目类型")
    @Excel(name = "项目类型")
    private String projectType;

    /** 关联合同id(s) */
    @ApiModelProperty(value = "关联合同id(s)")
    @Excel(name = "关联合同id(s)")
    private String relatedContracts;

    /** 客户信息ID */
    @ApiModelProperty(value = "客户信息ID")
    @Excel(name = "客户信息ID")
    private String clientId;

    /** 建设单位名称 */
    @ApiModelProperty(value = "建设单位名称")
    @Excel(name = "建设单位名称")
    private String conUnitName;

    /** 策划模板ID */
    @ApiModelProperty(value = "策划模板ID")
    @Excel(name = "策划模板ID")
    private String planningTemplateId;

    /** 设计类别 */
    @ApiModelProperty(value = "设计类别")
    @Excel(name = "设计类别")
    private String designCategory;

    /** 设计阶段 */
    @ApiModelProperty(value = "设计阶段")
    @Excel(name = "设计阶段")
    private String stageName;

    /** 项目状态 (0已备案 1进行中 2已暂停 3 已完成) */
    @ApiModelProperty(value = "项目状态 (0已备案 1进行中 2已暂停 3 已完成)")
    @Excel(name = "项目状态 (0已备案 1进行中 2已暂停 3 已完成)")
    private String projectStatus;

    /** 计划开始时间 */
    @ApiModelProperty(value = "计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartedDate;

    /** 计划结束时间 */
    @ApiModelProperty(value = "计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndDate;

    /** 项目所在年份 */
    @ApiModelProperty(value = "项目所在年份")
    @Excel(name = "项目所在年份")
    private String projectYear;

    /** 参与部门 */
    @ApiModelProperty(value = "参与部门")
    @Excel(name = "参与部门")
    private String participatingDept;

    /** 状态（0正常 1停用） */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /** 0-未同步 1-已同步 */
    @ApiModelProperty(value = "0-未同步 1-已同步")
    @Excel(name = "0-未同步 1-已同步")
    private String syncFlag;

    /** 同步协同后在协同的id */
    @ApiModelProperty(value = "同步协同后在协同的id")
    @Excel(name = "同步协同后在协同的id")
    private String cpProjectId;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setProjectNumber(String projectNumber) 
    {
        this.projectNumber = projectNumber;
    }

    public String getProjectNumber() 
    {
        return projectNumber;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setPrincipalContact(String principalContact) 
    {
        this.principalContact = principalContact;
    }

    public String getPrincipalContact() 
    {
        return principalContact;
    }
    public void setProjectType(String projectType) 
    {
        this.projectType = projectType;
    }

    public String getProjectType() 
    {
        return projectType;
    }
    public void setRelatedContracts(String relatedContracts) 
    {
        this.relatedContracts = relatedContracts;
    }

    public String getRelatedContracts() 
    {
        return relatedContracts;
    }
    public void setClientId(String clientId) 
    {
        this.clientId = clientId;
    }

    public String getClientId() 
    {
        return clientId;
    }
    public void setConUnitName(String conUnitName) 
    {
        this.conUnitName = conUnitName;
    }

    public String getConUnitName() 
    {
        return conUnitName;
    }
    public void setPlanningTemplateId(String planningTemplateId) 
    {
        this.planningTemplateId = planningTemplateId;
    }

    public String getPlanningTemplateId() 
    {
        return planningTemplateId;
    }
    public void setDesignCategory(String designCategory) 
    {
        this.designCategory = designCategory;
    }

    public String getDesignCategory() 
    {
        return designCategory;
    }
    public void setStageName(String stageName) 
    {
        this.stageName = stageName;
    }

    public String getStageName() 
    {
        return stageName;
    }
    public void setProjectStatus(String projectStatus) 
    {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatus() 
    {
        return projectStatus;
    }
    public void setPlanStartedDate(Date planStartedDate) 
    {
        this.planStartedDate = planStartedDate;
    }

    public Date getPlanStartedDate() 
    {
        return planStartedDate;
    }
    public void setPlanEndDate(Date planEndDate) 
    {
        this.planEndDate = planEndDate;
    }

    public Date getPlanEndDate() 
    {
        return planEndDate;
    }
    public void setProjectYear(String projectYear) 
    {
        this.projectYear = projectYear;
    }

    public String getProjectYear() 
    {
        return projectYear;
    }
    public void setParticipatingDept(String participatingDept) 
    {
        this.participatingDept = participatingDept;
    }

    public String getParticipatingDept() 
    {
        return participatingDept;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setSyncFlag(String syncFlag) 
    {
        this.syncFlag = syncFlag;
    }

    public String getSyncFlag() 
    {
        return syncFlag;
    }
    public void setCpProjectId(String cpProjectId) 
    {
        this.cpProjectId = cpProjectId;
    }

    public String getCpProjectId() 
    {
        return cpProjectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("projectNumber", getProjectNumber())
            .append("userId", getUserId())
            .append("principalContact", getPrincipalContact())
            .append("projectType", getProjectType())
            .append("relatedContracts", getRelatedContracts())
            .append("clientId", getClientId())
            .append("conUnitName", getConUnitName())
            .append("planningTemplateId", getPlanningTemplateId())
            .append("designCategory", getDesignCategory())
            .append("stageName", getStageName())
            .append("projectStatus", getProjectStatus())
            .append("planStartedDate", getPlanStartedDate())
            .append("planEndDate", getPlanEndDate())
            .append("projectYear", getProjectYear())
            .append("participatingDept", getParticipatingDept())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("syncFlag", getSyncFlag())
            .append("cpProjectId", getCpProjectId())
            .toString();
    }
}
