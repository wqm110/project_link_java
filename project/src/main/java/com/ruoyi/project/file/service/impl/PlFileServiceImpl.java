package com.ruoyi.project.file.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.file.domain.PlFile;
import com.ruoyi.project.file.mapper.PlFileMapper;
import com.ruoyi.project.file.service.IPlFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 我的文档-文件Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@Service
public class PlFileServiceImpl implements IPlFileService {
    @Autowired
    private PlFileMapper plFileMapper;

    /**
     * 查询我的文档-文件
     *
     * @param fileId 我的文档-文件ID
     * @return 我的文档-文件
     */
    @Override
    public PlFile selectPlFileById(String fileId) {
        return plFileMapper.selectPlFileById(fileId);
    }

    /**
     * 查询我的文档-文件列表
     *
     * @param plFile 我的文档-文件
     * @return 我的文档-文件
     */
    @Override
    public List<PlFile> selectPlFileList(PlFile plFile) {
        return plFileMapper.selectPlFileList(plFile);
    }

    /**
     * 新增我的文档-文件
     *
     * @param plFile 我的文档-文件
     * @return 结果
     */
    @Override
    public int insertPlFile(PlFile plFile) {
        plFile.setCreateTime(DateUtils.getNowDate());
        return plFileMapper.insertPlFile(plFile);
    }

    /**
     * 修改我的文档-文件
     *
     * @param plFile 我的文档-文件
     * @return 结果
     */
    @Override
    public int updatePlFile(PlFile plFile) {
        plFile.setUpdateTime(DateUtils.getNowDate());
        return plFileMapper.updatePlFile(plFile);
    }

    /**
     * 批量删除我的文档-文件
     *
     * @param fileIds 需要删除的我的文档-文件ID
     * @return 结果
     */
    @Override
    public int deletePlFileByIds(String[] fileIds) {
        return plFileMapper.deletePlFileByIds(fileIds);
    }

    /**
     * 删除我的文档-文件信息
     *
     * @param fileId 我的文档-文件ID
     * @return 结果
     */
    @Override
    public int deletePlFileById(String fileId) {
        return plFileMapper.deletePlFileById(fileId);
    }
}
