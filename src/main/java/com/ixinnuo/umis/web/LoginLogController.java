package com.ixinnuo.umis.web;

//import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ixinnuo.umis.dto.ResultInfo;
import com.ixinnuo.umis.entity.LoginLog;
import com.ixinnuo.umis.service.ILoginLogService;
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
 * 系统登录日志表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-01
 */
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

//    @Reference(version = "1.0.0")
    @Autowired
    private ILoginLogService iloginLogService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("loginLog:view")
    public @ResponseBody
    ResultInfo<List<LoginLog>> listData(String userName, String loginTime, Integer page, Integer limit){
        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(userName);
        EntityWrapper<LoginLog> wrapper = new EntityWrapper<>(loginLog);
        if(!StringUtils.isEmpty(loginTime)){
            wrapper.ge("create_time", FormatUtil.parseDate(loginTime.split(" - ")[0]+" 00:00:00", null));
            wrapper.le("create_time",FormatUtil.parseDate(loginTime.split(" - ")[1]+" 23:59:59", null));
        }
        wrapper.orderBy("create_time",false);
        Page<LoginLog> pageObj = iloginLogService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

}
