package com.ruoyi.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ruoyi.common.utils.CommonConstant;
import com.ruoyi.project.license.LicenseCheckInterceptor;

@Configuration
public class LoginLicenseVerifyConfig implements WebMvcConfigurer {
	
	@Bean
    public LicenseCheckInterceptor licenseCheckInterceptor()
    {
		LicenseCheckInterceptor lci = new LicenseCheckInterceptor();
        	return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        	registry.addInterceptor(licenseCheckInterceptor()).addPathPatterns(CommonConstant.LOGIN_URL).addPathPatterns(CommonConstant.LOGIN_PERSON_URL);
    }
}
