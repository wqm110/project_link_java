package com.ruoyi.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 当前在线会话
 * 
 * @author ruoyi
 */
@ApiModel(description = "当前在线会话")
public class SysUserOnline
{
    /** 会话编号 */
    @ApiModelProperty(value = "会话编号",notes = "")
    private String tokenId;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称",notes = "")
    private String deptName;

    /** 用户名称 */
    @ApiModelProperty(value = "用户名称",notes = "")
    private String userName;

    /** 登录IP地址 */
    @ApiModelProperty(value = "登录IP地址",notes = "")
    private String ipaddr;

    /** 登录地址 */
    @ApiModelProperty(value = "登录地址",notes = "")
    private String loginLocation;

    /** 浏览器类型 */
    @ApiModelProperty(value = "浏览器类型",notes = "")
    private String browser;

    /** 操作系统 */
    @ApiModelProperty(value = "操作系统",notes = "")
    private String os;

    /** 登录时间 */
    @ApiModelProperty(value = "登录时间",notes = "")
    private Long loginTime;

    public String getTokenId()
    {
        return tokenId;
    }

    public void setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }
}
