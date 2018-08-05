package com.information.project.bank.mapper;

import com.information.project.bank.domain.BankCheckbill;
import com.information.project.bank.domain.BankCheckbillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankCheckbillMapper {
    long countByExample(BankCheckbillExample example);

    int deleteByExample(BankCheckbillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BankCheckbill record);

    int insertSelective(BankCheckbill record);

    List<BankCheckbill> selectByExample(BankCheckbillExample example);

    BankCheckbill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BankCheckbill record, @Param("example") BankCheckbillExample example);

    int updateByExample(@Param("record") BankCheckbill record, @Param("example") BankCheckbillExample example);

    int updateByPrimaryKeySelective(BankCheckbill record);

    int updateByPrimaryKey(BankCheckbill record);
}