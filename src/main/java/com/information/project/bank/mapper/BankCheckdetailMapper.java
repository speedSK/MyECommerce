package com.information.project.bank.mapper;

import com.information.project.bank.domain.BankCheckdetail;
import com.information.project.bank.domain.BankCheckdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankCheckdetailMapper {
    long countByExample(BankCheckdetailExample example);

    int deleteByExample(BankCheckdetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BankCheckdetail record);

    int insertSelective(BankCheckdetail record);

    List<BankCheckdetail> selectByExample(BankCheckdetailExample example);

    BankCheckdetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BankCheckdetail record, @Param("example") BankCheckdetailExample example);

    int updateByExample(@Param("record") BankCheckdetail record, @Param("example") BankCheckdetailExample example);

    int updateByPrimaryKeySelective(BankCheckdetail record);

    int updateByPrimaryKey(BankCheckdetail record);
}