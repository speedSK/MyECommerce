package com.ruoyi.project.b2c.tradeRecord;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.tradeRecord.domain.TradeRecord;
import com.ruoyi.project.business.tradeRecord.service.ITradeRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/b2c/tradeRecord")
public class B2CTradeRecordController extends BaseController {
    private String prefix = "b2c/tradeRecord";
    @Autowired
    private ITradeRecordService tradeRecordService;

    @GetMapping()
    public String tradeRecord()
    {
        return prefix + "/tradeRecord";
    }

    /**
     * 查询流水列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TradeRecord tradeRecord)
    {
        startPage();
        List<TradeRecord> list = tradeRecordService.selectTradeRecordList(tradeRecord);
        return getDataTable(list);
    }
}
