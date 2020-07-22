package com.ixinnuo.umis.service;

import com.baomidou.mybatisplus.service.IService;
import com.ixinnuo.umis.entity.Role;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IRoleService extends IService<Role> {

    Boolean saveRole(Role role);

}