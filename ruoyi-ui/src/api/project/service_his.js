import request from '@/utils/request'

// 查询客户跟进记录列表
export function listService_his(query) {
  return request({
    url: '/project/service_his/list',
    method: 'get',
    params: query
  })
}

// 查询客户跟进记录详细
export function getService_his(serviceHisId) {
  return request({
    url: '/project/service_his/' + serviceHisId,
    method: 'get'
  })
}

// 新增客户跟进记录
export function addService_his(data) {
  return request({
    url: '/project/service_his',
    method: 'post',
    data: data
  })
}

// 修改客户跟进记录
export function updateService_his(data) {
  return request({
    url: '/project/service_his',
    method: 'put',
    data: data
  })
}

// 删除客户跟进记录
export function delService_his(serviceHisId) {
  return request({
    url: '/project/service_his/' + serviceHisId,
    method: 'delete'
  })
}

// 导出客户跟进记录
export function exportService_his(query) {
  return request({
    url: '/project/service_his/export',
    method: 'get',
    params: query
  })
}