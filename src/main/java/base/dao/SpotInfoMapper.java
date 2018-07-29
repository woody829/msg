package base.dao;

import base.model.SpotInfo;

public interface SpotInfoMapper {
    int insert(SpotInfo record);

    int insertSelective(SpotInfo record);
}