package com.ruoyi.project.file.mapper;

import java.util.List;
import com.ruoyi.project.file.domain.PlFile;

/**
 * 我的文档-文件Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
public interface PlFileMapper 
{
    /**
     * 查询我的文档-文件
     * 
     * @param fileId 我的文档-文件ID
     * @return 我的文档-文件
     */
    public PlFile selectPlFileById(String fileId);

    /**
     * 查询我的文档-文件列表
     * 
     * @param plFile 我的文档-文件
     * @return 我的文档-文件集合
     */
    public List<PlFile> selectPlFileList(PlFile plFile);

    /**
     * 新增我的文档-文件
     * 
     * @param plFile 我的文档-文件
     * @return 结果
     */
    public int insertPlFile(PlFile plFile);

    /**
     * 修改我的文档-文件
     * 
     * @param plFile 我的文档-文件
     * @return 结果
     */
    public int updatePlFile(PlFile plFile);

    /**
     * 删除我的文档-文件
     * 
     * @param fileId 我的文档-文件ID
     * @return 结果
     */
    public int deletePlFileById(String fileId);

    /**
     * 批量删除我的文档-文件
     * 
     * @param fileIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePlFileByIds(String[] fileIds);
}
