package base.dao;

import base.model.FutureInfo;

public interface FutureInfoMapper {
    int insert(FutureInfo record);

    int insertSelective(FutureInfo record);
}