import request from '@/utils/request'

// 查询投标信息申请列表
export function listBidinfo(query) {
  return request({
    url: '/project/bidinfo/list',
    method: 'get',
    params: query
  })
}

// 查询投标信息申请详细
export function getBidinfo(bidId) {
  return request({
    url: '/project/bidinfo/' + bidId,
    method: 'get'
  })
}

// 新增投标信息申请
export function addBidinfo(data) {
  return request({
    url: '/project/bidinfo',
    method: 'post',
    data: data
  })
}

// 修改投标信息申请
export function updateBidinfo(data) {
  return request({
    url: '/project/bidinfo',
    method: 'put',
    data: data
  })
}

// 删除投标信息申请
export function delBidinfo(bidId) {
  return request({
    url: '/project/bidinfo/' + bidId,
    method: 'delete'
  })
}

// 导出投标信息申请
export function exportBidinfo(query) {
  return request({
    url: '/project/bidinfo/export',
    method: 'get',
    params: query
  })
}