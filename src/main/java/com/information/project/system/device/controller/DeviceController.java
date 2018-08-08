package com.information.project.system.device.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.information.framework.aspectj.lang.annotation.Log;
import com.information.framework.aspectj.lang.constant.BusinessType;
import com.information.project.system.device.domain.Device;
import com.information.project.system.device.service.IDeviceService;
import com.information.framework.web.controller.BaseController;
import com.information.framework.web.page.TableDataInfo;
import com.information.framework.web.domain.AjaxResult;

/**
 * 设备 信息操作处理
 * 
 * @author LiuNing
 * @date 2018-08-06
 */
@Controller
@RequestMapping("/system/device")
public class DeviceController extends BaseController
{
    private String prefix = "system/device";
	
	@Autowired
	private IDeviceService deviceService;
	
	@RequiresPermissions("system:device:view")
	@GetMapping()
	public String device()
	{
	    return prefix + "/device";
	}
	
	/**
	 * 查询设备列表
	 */
	@RequiresPermissions("system:device:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Device device)
	{
		startPage();
        List<Device> list = deviceService.selectDeviceList(device);
		return getDataTable(list);
	}
	
	/**
	 * 新增设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备
	 */
	@RequiresPermissions("system:device:add")
	@Log(title = "设备", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Device device)
	{		
		return toAjax(deviceService.insertDevice(device));
	}

	/**
	 * 修改设备
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Device device = deviceService.selectDeviceById(id);
		mmap.put("device", device);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备
	 */
	@RequiresPermissions("system:device:edit")
	@Log(title = "设备", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Device device)
	{		
		return toAjax(deviceService.updateDevice(device));
	}
	
	/**
	 * 删除设备
	 */
	@RequiresPermissions("system:device:remove")
	@Log(title = "设备", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deviceService.deleteDeviceByIds(ids));
	}
	
}
