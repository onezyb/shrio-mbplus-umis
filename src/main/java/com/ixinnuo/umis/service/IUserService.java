package com.ixinnuo.umis.service;

import com.baomidou.mybatisplus.service.IService;
import com.ixinnuo.umis.dto.UserInfo;
import com.ixinnuo.umis.entity.User;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IUserService extends IService<User> {

    UserInfo findUserInfo(String userName);

}
