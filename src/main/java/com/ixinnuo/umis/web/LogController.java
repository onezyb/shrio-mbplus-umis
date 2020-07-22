package com.ixinnuo.umis.web;

//import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ixinnuo.umis.dto.ResultInfo;
import com.ixinnuo.umis.entity.Log;
import com.ixinnuo.umis.service.ILogService;
import com.ixinnuo.umis.util.FormatUtil;
import com.ixinnuo.umis.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-27
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {

//    @Reference(version = "1.0.0")
    @Autowired
    private ILogService ilogService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("log:view")
    public @ResponseBody
    ResultInfo<List<Log>> listData(String userName, String operTime, Integer page, Integer limit){
        Log log = new Log();
        log.setUserName(userName);
        EntityWrapper<Log> wrapper = new EntityWrapper<>(log);
        if(!StringUtils.isEmpty(operTime)){
            wrapper.ge("create_time", FormatUtil.parseDate(operTime.split(" - ")[0]+" 00:00:00", null));
            wrapper.le("create_time",FormatUtil.parseDate(operTime.split(" - ")[1]+" 23:59:59", null));
        }
        wrapper.orderBy("create_time",false);
        Page<Log> pageObj = ilogService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

}
