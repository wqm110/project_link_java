package com.ruoyi.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 项目进度计划对象 pl_project_schedule
 *
 * @author ruoyi
 * @date 2021-03-25
 */
@ApiModel(description = "项目进度计划对象")
public class PlProjectSchedule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 进度ID
     */
    @ApiModelProperty(value = "进度ID")
    private String scheduleId;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    @Excel(name = "项目ID")
    private String projectId;

    /**
     * 任务模式(数据字典)
     */
    @ApiModelProperty(value = "任务模式(数据字典)")
    @Excel(name = "任务模式(数据字典)")
    private String taskMode;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    @Excel(name = "任务名称")
    private String taskName;

    /**
     * 前置任务
     */
    @ApiModelProperty(value = "前置任务")
    @Excel(name = "前置任务")
    private Long predecessor;

    /**
     * 任务进度
     */
    @ApiModelProperty(value = "任务进度")
    @Excel(name = "任务进度")
    private Long taskProgress;

    /**
     * 任务工期
     */
    @ApiModelProperty(value = "任务工期")
    @Excel(name = "任务工期")
    private Long taskDuration;

    /**
     * 任务开始日期
     */
    @ApiModelProperty(value = "任务开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始日期" , width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskStartDate;

    /**
     * 任务完成日期
     */
    @ApiModelProperty(value = "任务完成日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务完成日期" , width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskCompletionDate;

    /**
     * 任务工时
     */
    @ApiModelProperty(value = "任务工时")
    @Excel(name = "任务工时")
    private Long taskHours;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门")
    @Excel(name = "部门")
    private Long taskDept;

    /**
     * 任务负责人ID(多个id用,分割)
     */
    @ApiModelProperty(value = "任务负责人ID(多个id用,分割)")
    @Excel(name = "任务负责人ID(多个id用,分割)")
    private String taskPrincipalId;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    @Excel(name = "资源名称")
    private String resourceName;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @Excel(name = "序号")
    private Long codeSerial;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    @Excel(name = "状态" , readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /**
     * 是否为 里程碑
     * 0 不是 默认
     * 1 是
     */
    @ApiModelProperty(value = "'是否为 里程碑   0不是 默认      1是     ")
    @Excel(name = "是否为 里程碑    0不是 默认       1是             ")
    private String milestoneFlag;

    /**
     * 进度是否完成
     * 0 未完成 默认
     * 1 完成    需手动修改表单
     */
    @ApiModelProperty(value = "进度是否完成  0未完成 默认   1完成 需手动修改表单      ")
    @Excel(name = "进度是否完成         0未完成 默认    1完成 需手动修改表单    ")
    private String finishedFlag;

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setTaskMode(String taskMode) {
        this.taskMode = taskMode;
    }

    public String getTaskMode() {
        return taskMode;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setPredecessor(Long predecessor) {
        this.predecessor = predecessor;
    }

    public Long getPredecessor() {
        return predecessor;
    }

    public void setTaskProgress(Long taskProgress) {
        this.taskProgress = taskProgress;
    }

    public Long getTaskProgress() {
        return taskProgress;
    }

    public void setTaskDuration(Long taskDuration) {
        this.taskDuration = taskDuration;
    }

    public Long getTaskDuration() {
        return taskDuration;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskCompletionDate(Date taskCompletionDate) {
        this.taskCompletionDate = taskCompletionDate;
    }

    public Date getTaskCompletionDate() {
        return taskCompletionDate;
    }

    public void setTaskHours(Long taskHours) {
        this.taskHours = taskHours;
    }

    public Long getTaskHours() {
        return taskHours;
    }

    public void setTaskDept(Long taskDept) {
        this.taskDept = taskDept;
    }

    public Long getTaskDept() {
        return taskDept;
    }

    public void setTaskPrincipalId(String taskPrincipalId) {
        this.taskPrincipalId = taskPrincipalId;
    }

    public String getTaskPrincipalId() {
        return taskPrincipalId;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setCodeSerial(Long codeSerial) {
        this.codeSerial = codeSerial;
    }

    public Long getCodeSerial() {
        return codeSerial;
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

    public void setMilestoneFlag(String milestoneFlag) {
        this.milestoneFlag = milestoneFlag;
    }

    public String getMilestoneFlag() {
        return milestoneFlag;
    }

    public void setFinishedFlag(String finishedFlag) {
        this.finishedFlag = finishedFlag;
    }

    public String getFinishedFlag() {
        return finishedFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("scheduleId" , getScheduleId())
                .append("projectId" , getProjectId())
                .append("taskMode" , getTaskMode())
                .append("taskName" , getTaskName())
                .append("predecessor" , getPredecessor())
                .append("taskProgress" , getTaskProgress())
                .append("taskDuration" , getTaskDuration())
                .append("taskStartDate" , getTaskStartDate())
                .append("taskCompletionDate" , getTaskCompletionDate())
                .append("taskHours" , getTaskHours())
                .append("taskDept" , getTaskDept())
                .append("taskPrincipalId" , getTaskPrincipalId())
                .append("resourceName" , getResourceName())
                .append("codeSerial" , getCodeSerial())
                .append("status" , getStatus())
                .append("delFlag" , getDelFlag())
                .append("createBy" , getCreateBy())
                .append("createTime" , getCreateTime())
                .append("updateBy" , getUpdateBy())
                .append("updateTime" , getUpdateTime())
                .append("remark" , getRemark())
                .append("milestoneFlag" , getMilestoneFlag())
                .append("finishedFlag" , getFinishedFlag())
                .toString();
    }
}