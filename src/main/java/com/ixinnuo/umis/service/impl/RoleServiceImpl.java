package com.ixinnuo.umis.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ixinnuo.umis.entity.Role;
import com.ixinnuo.umis.mapper.RoleMapper;
import com.ixinnuo.umis.service.IRoleService;
import org.springframework.stereotype.Service;
//import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
//@Service(version = "1.0.0", timeout = 60000)
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public Boolean saveRole(Role role) {
        Boolean res = false;
        if (role.getId() == null) {
            res = this.insert(role);
        } else {
            res = this.updateById(role);
        }
        return res;
    }
}