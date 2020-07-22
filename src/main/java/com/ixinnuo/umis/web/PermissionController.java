package com.ixinnuo.umis.web;

//import com.alibaba.dubbo.config.annotation.Reference;
import com.ixinnuo.umis.annotation.SysLog;
import com.ixinnuo.umis.code.ResourceType;
import com.ixinnuo.umis.dto.EnumInfo;
import com.ixinnuo.umis.dto.PermissionInfo;
import com.ixinnuo.umis.dto.ResultInfo;
import com.ixinnuo.umis.entity.Permission;
import com.ixinnuo.umis.service.IPermissionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 * 系统权限表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-10
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

//    @Reference(version = "1.0.0")
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/*")
    public void toHtml() {

    }

    @RequestMapping("/listData")
    @RequiresPermissions("permission:view")
    public @ResponseBody
    ResultInfo<List<Permission>> listData() {
        return new ResultInfo<>(iPermissionService.getAllPermissions());
    }

    @SysLog("保存权限操作")
    @RequestMapping("/save")
    @RequiresPermissions(value = {"permission:add", "permission:edit"}, logical = Logical.OR)
    public @ResponseBody
    ResultInfo<Boolean> save(Permission permission) {
        return new ResultInfo<>(iPermissionService.savePermission(permission));
    }

    @SysLog("删除权限操作")
    @RequestMapping("/delBatch")
    @RequiresPermissions("permission:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr) {
        return new ResultInfo<>(iPermissionService.delBatchPermission(Arrays.asList(idArr)));
    }

    @RequestMapping("/typeSelectData")
    public @ResponseBody
    ResultInfo<List<EnumInfo>> typeSelectData() {
        return new ResultInfo<>(ResourceType.getAllEnumInfo());
    }

    @RequestMapping("/parentSelectData")
    public @ResponseBody
    ResultInfo<List<Permission>> parentSelectData(String resourceType) {
        List<Permission> list = new ArrayList<>();
        if (resourceType.equals(ResourceType.TOP_DIRECTORY.getCode())){
            return new ResultInfo<>(list);
        } else if (resourceType.equals(ResourceType.DIRECTORY.getCode())) {
            List<Permission> allPermissions = iPermissionService.getAllPermissions();
            for (Permission p : allPermissions) {
                if (p.getResourceType().equals(ResourceType.TOP_DIRECTORY.getCode())) {
                    list.add(p);
                }
            }
        } else if (resourceType.equals(ResourceType.MENU.getCode())) {
            List<Permission> allPermissions = iPermissionService.getAllPermissions();
            for (Permission p : allPermissions) {
                if (p.getResourceType().equals(ResourceType.DIRECTORY.getCode()) ||
                        p.getResourceType().equals(ResourceType.TOP_DIRECTORY.getCode())) {
                    list.add(p);
                }
            }
        } else if (resourceType.equals(ResourceType.BUTTON.getCode())) {
            List<Permission> allPermissions = iPermissionService.getAllPermissions();
            for (Permission p : allPermissions) {
                if (p.getResourceType().equals(ResourceType.MENU.getCode())) {
                    list.add(p);
                }
            }
        }
        return new ResultInfo<>(list);
    }

    @RequestMapping("/xtreeData")
    public @ResponseBody
    ResultInfo<List<PermissionInfo>> xtreeData() {
        return new ResultInfo<>(iPermissionService.allPermissionInfo());
    }

}