package com.information.project.bank.mapper;

import com.information.project.bank.domain.BankRechargeRecord;
import com.information.project.bank.domain.BankRechargeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankRechargeRecordMapper {
    long countByExample(BankRechargeRecordExample example);

    int deleteByExample(BankRechargeRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BankRechargeRecord record);

    int insertSelective(BankRechargeRecord record);

    List<BankRechargeRecord> selectByExample(BankRechargeRecordExample example);

    BankRechargeRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BankRechargeRecord record, @Param("example") BankRechargeRecordExample example);

    int updateByExample(@Param("record") BankRechargeRecord record, @Param("example") BankRechargeRecordExample example);

    int updateByPrimaryKeySelective(BankRechargeRecord record);

    int updateByPrimaryKey(BankRechargeRecord record);
}