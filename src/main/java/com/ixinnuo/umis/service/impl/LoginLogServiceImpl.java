package com.ixinnuo.umis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ixinnuo.umis.entity.LoginLog;
import com.ixinnuo.umis.mapper.LoginLogMapper;
import com.ixinnuo.umis.service.ILoginLogService;
import org.springframework.stereotype.Service;
//import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统登录日志表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-01
 */
//@Service(version = "1.0.0", timeout = 60000)
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
