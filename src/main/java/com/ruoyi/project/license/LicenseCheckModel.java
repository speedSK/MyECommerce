package com.ruoyi.project.license;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义需要校验的License参数
 *
 * @author CoderX
 */
public class LicenseCheckModel implements Serializable{

    private static final long serialVersionUID = 8600137500316662317L;
    /**
     * 可被允许的IP地址
     */
//    private List<String> ipAddress;

    /**
     * 可被允许的MAC地址
     */
    private List<String> macAddress;

    /**
     * 可被允许的CPU序列号
     */
//    private String cpuSerial;

    /**
     * 可被允许的主板序列号
     */
//    private String mainBoardSerial;


    public List<String> getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(List<String> macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "LicenseCheckModel{" + "macAddress=" + macAddress + '}';
    }
}
