package base.dao;

import base.model.OrderInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoMapper {
    void deleteByPrimaryKey(String seqNo);

    void insert(OrderInfo record);

    void insertOrder(OrderInfo record);

    void insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String seqNo);

    void updateByPrimaryKeySelective(OrderInfo record);

    void updateByPrimaryKey(OrderInfo record);
}