package com.ruoyi.project.business.tradeRecord.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 流水 信息操作处理
 *
 * @author ruoyi
 * @date 2018-12-10
 */
@Controller
@RequestMapping("/business/tradeRecord")
public class TradeRecordController extends BaseController
{
    private String prefix = "business/tradeRecord";

    @Autowired
    private ITradeRecordService tradeRecordService;

    @RequiresPermissions("business:tradeRecord:view")
    @GetMapping()
    public String tradeRecord()
    {
        return prefix + "/tradeRecord";
    }

    /**
     * 查询流水列表
     */
    @RequiresPermissions("business:tradeRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TradeRecord tradeRecord)
    {
        startPage();
        List<TradeRecord> list = tradeRecordService.selectTradeRecordList(tradeRecord);
        return getDataTable(list);
    }


    /**
     * 导出流水列表
     */
    @RequiresPermissions("business:tradeRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TradeRecord tradeRecord)
    {
        List<TradeRecord> list = tradeRecordService.selectTradeRecordList(tradeRecord);
        ExcelUtil<TradeRecord> util = new ExcelUtil<TradeRecord>(TradeRecord.class);
        return util.exportExcel(list, "tradeRecord");
    }

    /**
     * 新增流水
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流水
     */
    @RequiresPermissions("business:tradeRecord:add")
    @Log(title = "流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TradeRecord tradeRecord)
    {
        return toAjax(tradeRecordService.insertTradeRecord(tradeRecord));
    }

    /**
     * 修改流水
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
		TradeRecord tradeRecord = tradeRecordService.selectTradeRecordById(id);
        mmap.put("tradeRecord", tradeRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存流水
     */
    @RequiresPermissions("business:tradeRecord:edit")
    @Log(title = "流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TradeRecord tradeRecord)
    {
        return toAjax(tradeRecordService.updateTradeRecord(tradeRecord));
    }

    /**
     * 删除流水
     */
    @RequiresPermissions("business:tradeRecord:remove")
    @Log(title = "流水", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tradeRecordService.deleteTradeRecordByIds(ids));
    }

}
