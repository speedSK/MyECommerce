package com.information.project.business.user.mapper;

import com.information.project.business.user.domain.BusUser;
import com.information.project.business.user.domain.BusUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusUserMapper {
    long countByExample(BusUserExample example);

    int deleteByExample(BusUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BusUser record);

    int insertSelective(BusUser record);

    List<BusUser> selectByExample(BusUserExample example);

    BusUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BusUser record, @Param("example") BusUserExample example);

    int updateByExample(@Param("record") BusUser record, @Param("example") BusUserExample example);

    int updateByPrimaryKeySelective(BusUser record);

    int updateByPrimaryKey(BusUser record);
}