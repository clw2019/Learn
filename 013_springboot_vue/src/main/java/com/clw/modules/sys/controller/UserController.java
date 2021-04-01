package com.clw.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.clw.common.constant.MyConstant;
import com.clw.common.result.Result;
import com.clw.modules.sys.entity.Permission;
import com.clw.modules.sys.model.MenuTreeModel;
import com.clw.modules.sys.service.PermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/14 21:22
 */
@Slf4j
@Api(tags = "用户菜单")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private PermissionService permissionService;

    /***
    * @Author: clw
    * @Description: 获取用户菜单
    * @Param: []
    * @return: com.clw.common.result.Result<java.util.List<com.clw.modules.sys.model.MenuTreeModel>>
    * @Date: 2021/3/14 21:26
    */
    @GetMapping("/getMenuList")
    public Result<List<MenuTreeModel>> getMenuList() {
        LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Permission::getDelFlag, MyConstant.DEL_FLAG_0)
                .orderByAsc(Permission::getSortNo);
        List<Permission> list = permissionService.list(lambdaQueryWrapper);
        List<MenuTreeModel> treeList = new ArrayList<>();
        buildTree(treeList, list, null);
        return Result.success(treeList);
    }

    /***
    * @Author: clw
    * @Description: 构建树形菜单
    * @Param: [treeList, list, temp]
    * @return: void
    * @Date: 2021/3/18 22:11
    */
    private void buildTree(List<MenuTreeModel> treeList, List<Permission> list, MenuTreeModel temp) {

        for (Permission permission : list) {  // 2.0
            String parentId = permission.getParentId();
            // 用有参构造函数 ==> MenuTreeModel menuTreeModel = new MenuTreeModel() 然后set
            MenuTreeModel menuTreeModel = new MenuTreeModel(permission);
            if (temp == null && StringUtils.isEmpty(parentId)) { // 1.1第一次遍历的时候，先获取 parentId 为空的 一级菜单
                treeList.add(menuTreeModel);
                if (!menuTreeModel.getIsLeaf()) { // 1.2 判断当前菜单是否是叶子结点 是 => 即 没有下级菜单 => 继续循环
                    buildTree(treeList, list, menuTreeModel);  // 1.3 不是叶子节点 => 即 有下级菜单 => 递归 调用
                }
            } else if (temp != null && parentId != null && parentId.equals(temp.getId())){ // 2.1 temp = 父菜单 parentId.equals(temp.getId()) true
                temp.getChild().add(menuTreeModel); // 2.2 将子菜单 放入 child
                if (!menuTreeModel.getIsLeaf()) {
                    buildTree(treeList, list, menuTreeModel);  // 2.3 递归判断 子菜单 是否 有子菜单
                }
            }
        }
    }
}
