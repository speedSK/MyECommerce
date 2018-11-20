package com.ruoyi.project.system.device.service;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.dict.mapper.DictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.device.mapper.DeviceMapper;
import com.ruoyi.project.system.device.domain.Device;
import com.ruoyi.common.support.Convert;

/**
 * 设备 服务层实现
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Service
public class DeviceServiceImpl implements IDeviceService 
{
	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private DictDataMapper dictDataMapper;

	/**
     * 查询设备信息
     * 
     * @param id 设备ID
     * @return 设备信息
     */
    @Override
	public Device selectDeviceById(Long id)
	{
	    return deviceMapper.selectDeviceById(id);
	}
	
	/**
     * 查询设备列表
     * 
     * @param device 设备信息
     * @return 设备集合
     */
	@Override
	public List<Device> selectDeviceList(Device device)
	{
		if(StringUtils.isEmpty(device.getStatus())){
			device.setStatus(Constants.STATUS_ACTIVE);
		}
	    return deviceMapper.selectDeviceList(device);
	}
	
    /**
     * 新增设备
     * 
     * @param device 设备信息
     * @return 结果
     */
	@Override
	public int insertDevice(Device device)
	{

		device.setStatus(Constants.STATUS_ACTIVE);

		device.setCreateBy(ShiroUtils.getLoginName());


		return deviceMapper.insertDevice(device);
	}
	
	/**
     * 修改设备
     * 
     * @param device 设备信息
     * @return 结果
     */
	@Override
	public int updateDevice(Device device){


		//初始化數據信息

		device.setUpdateBy(ShiroUtils.getLoginName());


		return deviceMapper.updateDevice(device);
	}

	/**
     * 删除设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果F
     */
	@Override
	public int deleteDeviceByIds(String ids)
	{

		String [] idsArray = Convert.toStrArray(ids);
		for (String id: idsArray) {
			Device device = new Device();
			device.setId(Long.valueOf(id));

			device.setStatus(Constants.STATUS_REMOVED);

			device.setUpdateBy(ShiroUtils.getLoginName());

			deviceMapper.updateDevice(device);

		}

		return 1;
	}
	
}
