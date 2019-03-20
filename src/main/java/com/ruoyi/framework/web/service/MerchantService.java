package com.ruoyi.framework.web.service;

import com.ruoyi.project.system.merchant.domain.Merchant;
import com.ruoyi.project.system.merchant.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("merchant")
public class MerchantService {
    @Autowired
    private IMerchantService merchantService;

    public List<Merchant> getAllMerchant() {
        return merchantService.selectMerchantAll();
    }

}
