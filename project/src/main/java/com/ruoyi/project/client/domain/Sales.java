package com.ruoyi.project.client.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

@ApiModel(description = "销售人员")
public class Sales extends SysUser {
    @ApiModelProperty(value = "销售所服务的客户圈", example = "[]")
    private ArrayList<PlClient> clients;

    public ArrayList<PlClient> getClients() {
        return clients;
    }

    public void setClients(ArrayList<PlClient> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "销售：" + this.getUserName() + "\n 客户：" + this.clients;
    }
}
