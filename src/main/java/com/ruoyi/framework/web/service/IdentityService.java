package com.ruoyi.framework.web.service;

import com.ruoyi.project.system.identity.domain.Identity;
import com.ruoyi.project.system.identity.service.IIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("identity")
public class IdentityService {
    @Autowired
    private IIdentityService iIdentityService;

    public List<Identity> getIdentities() {
        return iIdentityService.selectPostAll();
    }
}
