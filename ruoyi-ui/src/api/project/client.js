import request from '@/utils/request'

// 查询客户信息列表
export function listClient(query) {
  return request({
    url: '/project/client/list',
    method: 'get',
    params: query
  })
}

// 查询客户信息详细
export function getClient(clientId) {
  return request({
    url: '/project/client/' + clientId,
    method: 'get'
  })
}

// 新增客户信息
export function addClient(data) {
  return request({
    url: '/project/client',
    method: 'post',
    data: data
  })
}

// 修改客户信息
export function updateClient(data) {
  return request({
    url: '/project/client',
    method: 'put',
    data: data
  })
}

// 删除客户信息
export function delClient(clientId) {
  return request({
    url: '/project/client/' + clientId,
    method: 'delete'
  })
}

// 导出客户信息
export function exportClient(query) {
  return request({
    url: '/project/client/export',
    method: 'get',
    params: query
  })
}