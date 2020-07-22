package com.ixinnuo.umis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ixinnuo.umis.entity.Log;
import com.ixinnuo.umis.mapper.LogMapper;
import com.ixinnuo.umis.service.ILogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-27
 */
//@Service(version = "1.0.0", timeout = 60000)
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
