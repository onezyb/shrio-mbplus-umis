package com.ixinnuo.umis.service;

import com.baomidou.mybatisplus.service.IService;
import com.ixinnuo.umis.dto.MenuInfo;
import com.ixinnuo.umis.dto.PermissionInfo;
import com.ixinnuo.umis.entity.Permission;

import java.util.List;

/**
 * <p>
 * 系统权限表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IPermissionService extends IService<Permission> {

     List<Permission> getAllPermissions();

     Boolean savePermission(Permission permission);

     Boolean delBatchPermission(List<Integer> ids);

     List<PermissionInfo> allPermissionInfo();

     List<MenuInfo> getMenuPermissions(String code);

     List<Permission> getTopDirectoryPermissions();

}