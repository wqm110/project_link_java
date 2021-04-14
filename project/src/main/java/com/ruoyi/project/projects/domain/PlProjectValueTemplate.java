package com.ruoyi.project.projects.domain;

import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目产值分配模板对象 pl_project_value_template
 * 
 * @author wqm
 * @date 2021-04-14
 */
@ApiModel(description= "项目产值分配模板对象")
public class PlProjectValueTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分配ID */
    @ApiModelProperty(value = "分配ID")
    private String tmeplateId;

    /** 名称 */
    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String tmeplateName;

    /** 分配基数 */
    @ApiModelProperty(value = "分配基数")
    @Excel(name = "分配基数")
    private BigDecimal allocationBase;

    /** 类型系数 */
    @ApiModelProperty(value = "类型系数")
    @Excel(name = "类型系数")
    private BigDecimal typeCoefficient;

    /** 难度系数 */
    @ApiModelProperty(value = "难度系数")
    @Excel(name = "难度系数")
    private BigDecimal difficulty;

    /** 阶段系数 */
    @ApiModelProperty(value = "阶段系数")
    @Excel(name = "阶段系数")
    private BigDecimal stageCoefficient;

    /** 套图系数 */
    @ApiModelProperty(value = "套图系数")
    @Excel(name = "套图系数")
    private BigDecimal setsCoefficient;

    /** 变更系数 */
    @ApiModelProperty(value = "变更系数")
    @Excel(name = "变更系数")
    private BigDecimal alterCoefficient;

    /** 项目负责人系数 */
    @ApiModelProperty(value = "项目负责人系数")
    @Excel(name = "项目负责人系数")
    private BigDecimal pmCoefficient;

    /** 状态（0正常 1停用） */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    public void setTmeplateId(String tmeplateId) 
    {
        this.tmeplateId = tmeplateId;
    }

    public String getTmeplateId() 
    {
        return tmeplateId;
    }
    public void setTmeplateName(String tmeplateName) 
    {
        this.tmeplateName = tmeplateName;
    }

    public String getTmeplateName() 
    {
        return tmeplateName;
    }
    public void setAllocationBase(BigDecimal allocationBase) 
    {
        this.allocationBase = allocationBase;
    }

    public BigDecimal getAllocationBase() 
    {
        return allocationBase;
    }
    public void setTypeCoefficient(BigDecimal typeCoefficient) 
    {
        this.typeCoefficient = typeCoefficient;
    }

    public BigDecimal getTypeCoefficient() 
    {
        return typeCoefficient;
    }
    public void setDifficulty(BigDecimal difficulty) 
    {
        this.difficulty = difficulty;
    }

    public BigDecimal getDifficulty() 
    {
        return difficulty;
    }
    public void setStageCoefficient(BigDecimal stageCoefficient) 
    {
        this.stageCoefficient = stageCoefficient;
    }

    public BigDecimal getStageCoefficient() 
    {
        return stageCoefficient;
    }
    public void setSetsCoefficient(BigDecimal setsCoefficient) 
    {
        this.setsCoefficient = setsCoefficient;
    }

    public BigDecimal getSetsCoefficient() 
    {
        return setsCoefficient;
    }
    public void setAlterCoefficient(BigDecimal alterCoefficient) 
    {
        this.alterCoefficient = alterCoefficient;
    }

    public BigDecimal getAlterCoefficient() 
    {
        return alterCoefficient;
    }
    public void setPmCoefficient(BigDecimal pmCoefficient) 
    {
        this.pmCoefficient = pmCoefficient;
    }

    public BigDecimal getPmCoefficient() 
    {
        return pmCoefficient;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tmeplateId", getTmeplateId())
            .append("tmeplateName", getTmeplateName())
            .append("allocationBase", getAllocationBase())
            .append("typeCoefficient", getTypeCoefficient())
            .append("difficulty", getDifficulty())
            .append("stageCoefficient", getStageCoefficient())
            .append("setsCoefficient", getSetsCoefficient())
            .append("alterCoefficient", getAlterCoefficient())
            .append("pmCoefficient", getPmCoefficient())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
