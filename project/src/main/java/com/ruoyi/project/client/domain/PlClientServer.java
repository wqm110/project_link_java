package com.ruoyi.project.client.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户-业务员服务关系对象 pl_client_server
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@ApiModel(description = "客户-业务员服务关系对象")
public class PlClientServer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long clientId;

    /**
     * 业务员id
     */
    @ApiModelProperty(value = "业务员id")
    private Long userId;

    /**
     * $column.columnComment
     */
    @ApiModelProperty(value = "$column.columnComment")
    private String delFlag="0";

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}