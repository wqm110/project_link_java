import request from '@/utils/request'

// 查询客户-业务员服务关系列表
export function listClient_server(query) {
  return request({
    url: '/project/client_server/list',
    method: 'get',
    params: query
  })
}

// 查询客户-业务员服务关系详细
export function getClient_server(clientId) {
  return request({
    url: '/project/client_server/' + clientId,
    method: 'get'
  })
}

// 新增客户-业务员服务关系
export function addClient_server(data) {
  return request({
    url: '/project/client_server',
    method: 'post',
    data: data
  })
}

// 修改客户-业务员服务关系
export function updateClient_server(data) {
  return request({
    url: '/project/client_server',
    method: 'put',
    data: data
  })
}

// 删除客户-业务员服务关系
export function delClient_server(clientId) {
  return request({
    url: '/project/client_server/' + clientId,
    method: 'delete'
  })
}

// 导出客户-业务员服务关系
export function exportClient_server(query) {
  return request({
    url: '/project/client_server/export',
    method: 'get',
    params: query
  })
}