package com.ixinnuo.umis.web;

//import com.alibaba.dubbo.config.annotation.Reference;
import com.ixinnuo.umis.dto.MenuInfo;
import com.ixinnuo.umis.dto.ResultInfo;
import com.ixinnuo.umis.dto.RoleInfo;
import com.ixinnuo.umis.dto.UserInfo;
import com.ixinnuo.umis.service.IPermissionService;
import com.ixinnuo.umis.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author 邪客
 * @since 2018-07-16
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

//    @Reference(version = "1.0.0")
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/left")
    @ResponseBody
    public ResultInfo<List<MenuInfo>> getMenuInfoList(@RequestParam("code") String code){
        List<MenuInfo> menuInfoList = new ArrayList<>();
        //获取当前用户角色信息
        UserInfo userInfo = this.getUserInfo();
        RoleInfo roleInfo = userInfo.getRoleInfo();
        if (roleInfo == null || StringUtils.isEmpty(roleInfo.getPermissionIds())){
            return new ResultInfo<>(menuInfoList);
        }
        //获取权限菜单信息
        List<MenuInfo>  allMenuInfoList = iPermissionService.getMenuPermissions(code);
        if (allMenuInfoList == null || allMenuInfoList.isEmpty()){
            return new ResultInfo<>(menuInfoList);
        }
        //获得有权限访问菜单
        for (MenuInfo info : allMenuInfoList) {
            if(roleInfo.getPermissionIds().contains(","+info.getOnlyId()+",")){
                if (info.getChildren() != null){
                    List<MenuInfo> subMenuInfoList = new ArrayList<>();
                    for (MenuInfo subInfo : info.getChildren()) {
                        if (roleInfo.getPermissionIds().contains(","+subInfo.getOnlyId()+",")){
                            subMenuInfoList.add(subInfo);
                        }
                    }
                    info.setChildren(subMenuInfoList);
                }
                menuInfoList.add(info);
            }
        }
        return new ResultInfo<>(menuInfoList);
    }

}
