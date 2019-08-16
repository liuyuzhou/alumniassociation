package com.alumniassociation.web.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.alumniassociation.common.dao.BaseDao;
import com.alumniassociation.web.common.entity.File;

/**
 * 地产附件表
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-17 11:52:01
 */
public interface FileDao extends BaseDao<File> {

    List<File> getByRelationId(String relationId);

    List<File> getFileList(String imgUUID);

    List<File> getFileListByUUID(List<String> list);

    void deleteByRelationId(String relationId);
}
