package com.ixinnuo.umis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ixinnuo.umis.dto.UserInfo;
import com.ixinnuo.umis.entity.User;
import com.ixinnuo.umis.mapper.UserMapper;
import com.ixinnuo.umis.service.IUserService;
import org.springframework.stereotype.Service;
//import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
//@Service(version = "1.0.0", timeout = 60000)
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserInfo findUserInfo(String userName) {
        return this.baseMapper.findUserInfo(userName);
    }
}