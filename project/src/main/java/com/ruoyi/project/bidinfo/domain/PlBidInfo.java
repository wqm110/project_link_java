package com.ruoyi.project.bidinfo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.project.client.domain.PlClient;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 投标信息申请对象 pl_bid_info
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@ApiModel(description = "投标信息申请对象")
public class PlBidInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 申请ID
     */
    @ApiModelProperty(value = "申请ID")
    private Long bidId;

    /**
     * 申请人ID
     */
    @ApiModelProperty(value = "申请人ID")
    @Excel(name = "申请人ID")
    private Long userId;

    /**
     * 标书名称
     */
    @ApiModelProperty(value = "标书名称")
    @Excel(name = "标书名称")
    private String bidName;

    /**
     * 标书编号
     */
    @ApiModelProperty(value = "标书编号")
    @Excel(name = "标书编号")
    private String bidNumber;

    /**
     * 投标截止日期
     */
    @ApiModelProperty(value = "投标截止日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投标截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bidDeadline;

    /**
     * 投标日期
     */
    @ApiModelProperty(value = "投标日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投标日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bidDate;

    /**
     * 中标日期
     */
    @ApiModelProperty(value = "中标日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "中标日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date winningDate;

    /**
     * 开标日期
     */
    @ApiModelProperty(value = "开标日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开标日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bidOpeningDate;

    /**
     * 招标代理公司
     */
    @ApiModelProperty(value = "招标代理公司")
    @Excel(name = "招标代理公司")
    private String tenderAgency;

    /**
     * 招标单位
     */
    @ApiModelProperty(value = "招标单位")
    @Excel(name = "招标单位")
    private String biddingUnit;

    /**
     * 报价(元)
     */
    @ApiModelProperty(value = "报价(元)")
    @Excel(name = "报价(元)")
    private BigDecimal quotedPrice;

    /**
     * 建设地点
     */
    @ApiModelProperty(value = "建设地点")
    @Excel(name = "建设地点")
    private String constructionSite;

    /**
     * 投标保证金(元)
     */
    @ApiModelProperty(value = "投标保证金(元)")
    @Excel(name = "投标保证金(元)")
    private BigDecimal bidBond;

    /**
     * 是否中标(0否 1是)
     */
    @ApiModelProperty(value = "是否中标(0否 1是)")
    @Excel(name = "是否中标(0否 1是)")
    private String winBid;

    /**
     * 投标保证金是否收回(0否 1是)
     */
    @ApiModelProperty(value = "投标保证金是否收回(0否 1是)")
    @Excel(name = "投标保证金是否收回(0否 1是)")
    private String bidBondRecovered;

    /**
     * 投标保证金收回日期
     */
    @ApiModelProperty(value = "投标保证金收回日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投标保证金收回日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bidBondTakebackDate;

    /**
     * 投标保证金未收回原因
     */
    @ApiModelProperty(value = "投标保证金未收回原因")
    @Excel(name = "投标保证金未收回原因")
    private String bidBondReasonForFailure;

    /**
     * 履约保证金(元)
     */
    @ApiModelProperty(value = "履约保证金(元)")
    @Excel(name = "履约保证金(元)")
    private BigDecimal performBond;

    /**
     * 履约保证金是否收回(0否 1是)
     */
    @ApiModelProperty(value = "履约保证金是否收回(0否 1是)")
    @Excel(name = "履约保证金是否收回(0否 1是)")
    private String performBondRecovered;

    /**
     * 履约保证金收回日期
     */
    @ApiModelProperty(value = "履约保证金收回日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "履约保证金收回日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date performBondTakebackDate;

    /**
     * 履约保证金未收回原因
     */
    @ApiModelProperty(value = "履约保证金未收回原因")
    @Excel(name = "履约保证金未收回原因")
    private String performBondReasonForFailure;

    /**
     * 中标通知书附件信息(前端自主构建JSON格式字符串)
     */
    @ApiModelProperty(value = "中标通知书附件信息(前端自主构建JSON格式字符串)")
    @Excel(name = "中标通知书附件信息(前端自主构建JSON格式字符串)")
    private String bidAttachment;

    /**
     * 流程实例ID
     */
    @ApiModelProperty(value = "流程实例ID")
    @Excel(name = "流程实例ID")
    private String instanceId;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（0正常 1停用）")
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)
     */
    @ApiModelProperty(value = "申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)")
    @Excel(name = "申请状态(0-未提交 1-审批中 2-被退回 3-审批通过 4-审批未通过 5-已撤销)")
    private String applyStatus;

    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private String delFlag = "0";

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /**
     * 投标项目
     */
    @ApiModelProperty(value = "投标项目")
    @Excel(name = "投标项目")
    private Long projectId;

    /**
     * 项目类别
     */
    @ApiModelProperty(value = "项目类别")
    @Excel(name = "项目类别")
    private String projectCategory;

    /**
     * 项目负责人
     */
    @ApiModelProperty(value = "项目负责人")
    @Excel(name = "项目负责人")
    private String projectManager;

    /**
     * 项目负责人姓名
     */
    @ApiModelProperty(value = "项目负责人姓名")
    @Excel(name = "项目负责人姓名")
    private String projectManagerName;

    /**
     * 项目负责人电话
     */
    @ApiModelProperty(value = "项目负责人电话")
    @Excel(name = "项目负责人电话")
    private String projectManagerContact;

    /**
     * 经办人
     */
    @ApiModelProperty(value = "经办人")
    @Excel(name = "经办人")
    private String agent;

    /**
     * 经办人姓名
     */
    @ApiModelProperty(value = "经办人姓名")
    @Excel(name = "经办人姓名")
    private String agentName;

    /**
     * 经办人电话
     */
    @ApiModelProperty(value = "经办人电话")
    @Excel(name = "经办人电话")
    private String agentContact;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    @Excel(name = "项目类型")
    private String projectType;

    /**
     * 规模
     */
    @ApiModelProperty(value = "规模")
    @Excel(name = "规模")
    private String scale;

    /**
     * 业务类别
     */
    @ApiModelProperty(value = "业务类别")
    @Excel(name = "业务类别")
    private String businessCategory;

    /**
     * 联合单位名称
     */
    @ApiModelProperty(value = "联合单位名称")
    @Excel(name = "联合单位名称")
    private String unionCompanyName;

    /**
     * 投标地点
     */
    @ApiModelProperty(value = "投标地点")
    @Excel(name = "投标地点")
    private String bidPlace;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String nickName;

    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private SysUser user;

    /**
     * 招标单位
     */
    @ApiModelProperty(value = "招标单位")
    private String clientName;

    /**
     * 项目负责人电话
     */
    @ApiModelProperty(value = "项目负责人电话")
    private String phonenumber;
    /**
     * 经办人电话
     */
    @ApiModelProperty(value = "经办人电话")
    private String phoneNum;


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    private PlClient client;

    public PlClient getClient() {
        return client;
    }

    public void setClient(PlClient client) {
        this.client = client;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }


    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidDeadline(Date bidDeadline) {
        this.bidDeadline = bidDeadline;
    }

    public Date getBidDeadline() {
        return bidDeadline;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setWinningDate(Date winningDate) {
        this.winningDate = winningDate;
    }

    public Date getWinningDate() {
        return winningDate;
    }

    public void setBidOpeningDate(Date bidOpeningDate) {
        this.bidOpeningDate = bidOpeningDate;
    }

    public Date getBidOpeningDate() {
        return bidOpeningDate;
    }

    public void setTenderAgency(String tenderAgency) {
        this.tenderAgency = tenderAgency;
    }

    public String getTenderAgency() {
        return tenderAgency;
    }

    public void setBiddingUnit(String biddingUnit) {
        this.biddingUnit = biddingUnit;
    }

    public String getBiddingUnit() {
        return biddingUnit;
    }

    public void setQuotedPrice(BigDecimal quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public BigDecimal getQuotedPrice() {
        return quotedPrice;
    }

    public void setConstructionSite(String constructionSite) {
        this.constructionSite = constructionSite;
    }

    public String getConstructionSite() {
        return constructionSite;
    }

    public void setBidBond(BigDecimal bidBond) {
        this.bidBond = bidBond;
    }

    public BigDecimal getBidBond() {
        return bidBond;
    }

    public void setWinBid(String winBid) {
        this.winBid = winBid;
    }

    public String getWinBid() {
        return winBid;
    }

    public void setBidBondRecovered(String bidBondRecovered) {
        this.bidBondRecovered = bidBondRecovered;
    }

    public String getBidBondRecovered() {
        return bidBondRecovered;
    }

    public void setBidBondTakebackDate(Date bidBondTakebackDate) {
        this.bidBondTakebackDate = bidBondTakebackDate;
    }

    public Date getBidBondTakebackDate() {
        return bidBondTakebackDate;
    }

    public void setBidBondReasonForFailure(String bidBondReasonForFailure) {
        this.bidBondReasonForFailure = bidBondReasonForFailure;
    }

    public String getBidBondReasonForFailure() {
        return bidBondReasonForFailure;
    }

    public void setPerformBond(BigDecimal performBond) {
        this.performBond = performBond;
    }

    public BigDecimal getPerformBond() {
        return performBond;
    }

    public void setPerformBondRecovered(String performBondRecovered) {
        this.performBondRecovered = performBondRecovered;
    }

    public String getPerformBondRecovered() {
        return performBondRecovered;
    }

    public void setPerformBondTakebackDate(Date performBondTakebackDate) {
        this.performBondTakebackDate = performBondTakebackDate;
    }

    public Date getPerformBondTakebackDate() {
        return performBondTakebackDate;
    }

    public void setPerformBondReasonForFailure(String performBondReasonForFailure) {
        this.performBondReasonForFailure = performBondReasonForFailure;
    }

    public String getPerformBondReasonForFailure() {
        return performBondReasonForFailure;
    }

    public void setBidAttachment(String bidAttachment) {
        this.bidAttachment = bidAttachment;
    }

    public String getBidAttachment() {
        return bidAttachment;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus() {
        return applyStatus;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerContact(String projectManagerContact) {
        this.projectManagerContact = projectManagerContact;
    }

    public String getProjectManagerContact() {
        return projectManagerContact;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentContact(String agentContact) {
        this.agentContact = agentContact;
    }

    public String getAgentContact() {
        return agentContact;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getScale() {
        return scale;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setUnionCompanyName(String unionCompanyName) {
        this.unionCompanyName = unionCompanyName;
    }

    public String getUnionCompanyName() {
        return unionCompanyName;
    }

    public void setBidPlace(String bidPlace) {
        this.bidPlace = bidPlace;
    }

    public String getBidPlace() {
        return bidPlace;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("bidId", getBidId())
                .append("userId", getUserId())
                .append("bidName", getBidName())
                .append("bidNumber", getBidNumber())
                .append("bidDeadline", getBidDeadline())
                .append("bidDate", getBidDate())
                .append("winningDate", getWinningDate())
                .append("bidOpeningDate", getBidOpeningDate())
                .append("tenderAgency", getTenderAgency())
                .append("biddingUnit", getBiddingUnit())
                .append("quotedPrice", getQuotedPrice())
                .append("constructionSite", getConstructionSite())
                .append("bidBond", getBidBond())
                .append("winBid", getWinBid())
                .append("bidBondRecovered", getBidBondRecovered())
                .append("bidBondTakebackDate", getBidBondTakebackDate())
                .append("bidBondReasonForFailure", getBidBondReasonForFailure())
                .append("performBond", getPerformBond())
                .append("performBondRecovered", getPerformBondRecovered())
                .append("performBondTakebackDate", getPerformBondTakebackDate())
                .append("performBondReasonForFailure", getPerformBondReasonForFailure())
                .append("bidAttachment", getBidAttachment())
                .append("instanceId", getInstanceId())
                .append("status", getStatus())
                .append("applyStatus", getApplyStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("applyTime", getApplyTime())
                .append("projectId", getProjectId())
                .append("projectCategory", getProjectCategory())
                .append("projectManager", getProjectManager())
                .append("projectManagerName", getProjectManagerName())
                .append("projectManagerContact", getProjectManagerContact())
                .append("agent", getAgent())
                .append("agentName", getAgentName())
                .append("agentContact", getAgentContact())
                .append("projectType", getProjectType())
                .append("scale", getScale())
                .append("businessCategory", getBusinessCategory())
                .append("unionCompanyName", getUnionCompanyName())
                .append("bidPlace", getBidPlace())
                .append("clientName", getClientName())
                .append("phonenumber", getPhonenumber())
                .append("phonenNum", getPhoneNum())
                .toString();
    }
}
