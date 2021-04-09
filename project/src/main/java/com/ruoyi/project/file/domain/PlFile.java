package com.ruoyi.project.file.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的文档-文件对象 pl_file
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@ApiModel(description = "我的文档-文件对象")
public class PlFile extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String fileId;

    /**
     * 文件夹ID
     */
    @ApiModelProperty(value = "文件夹ID")
    @Excel(name = "文件夹ID")
    private String folderId;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    @Excel(name = "文件名称")
    private String fileName;

    /**
     * 文件绝对路径/计算机本地文件路径
     */
    @ApiModelProperty(value = "文件绝对路径/计算机本地文件路径")
    @Excel(name = "文件绝对路径/计算机本地文件路径")
    private String fileRealPath;

    /**
     * 文件相对路径/url除了主域名后地址
     */
    @ApiModelProperty(value = "文件相对路径/url除了主域名后地址")
    @Excel(name = "文件相对路径/url除了主域名后地址")
    private String fileUrlPath;

    /**
     * 文件扩展名
     */
    @ApiModelProperty(value = "文件扩展名")
    @Excel(name = "文件扩展名")
    private String fileExtension;

    /**
     * 文件上传时的名称/带扩展名
     */
    @ApiModelProperty(value = "文件上传时的名称/带扩展名")
    @Excel(name = "文件上传时的名称/带扩展名")
    private String fileNameBefore;

    /**
     * 功能模块关键字
     */
    @ApiModelProperty(value = "功能模块关键字")
    @Excel(name = "功能模块关键字")
    private String functionKey;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    @Excel(name = "部门ID")
    private Long deptId;

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
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    @Excel(name = "文件大小")
    private String fileSize;

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileRealPath(String fileRealPath) {
        this.fileRealPath = fileRealPath;
    }

    public String getFileRealPath() {
        return fileRealPath;
    }

    public void setFileUrlPath(String fileUrlPath) {
        this.fileUrlPath = fileUrlPath;
    }

    public String getFileUrlPath() {
        return fileUrlPath;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileNameBefore(String fileNameBefore) {
        this.fileNameBefore = fileNameBefore;
    }

    public String getFileNameBefore() {
        return fileNameBefore;
    }

    public void setFunctionKey(String functionKey) {
        this.functionKey = functionKey;
    }

    public String getFunctionKey() {
        return functionKey;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
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

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fileId", getFileId())
                .append("folderId", getFolderId())
                .append("fileName", getFileName())
                .append("fileRealPath", getFileRealPath())
                .append("fileUrlPath", getFileUrlPath())
                .append("fileExtension", getFileExtension())
                .append("fileNameBefore", getFileNameBefore())
                .append("functionKey", getFunctionKey())
                .append("userId", getUserId())
                .append("deptId", getDeptId())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("fileSize", getFileSize())
                .toString();
    }

    public static void main(String[] args) {
        System.out.println("args = " + args);
    }
}
