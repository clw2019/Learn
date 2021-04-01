package com.clw.modules.sys.model;

import com.clw.modules.sys.entity.Permission;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/14 21:01
 */
@Data
public class MenuTreeModel {

    private String id;
    /**
    * 菜单名称
    */
    private String name;
    /**
     * 菜单名称
     */
    private String parentId;
    /**
     * 菜单权限编码
     */
    private String perms;
    /**
     * 菜单类型（0：一级菜单、1：子菜单、2：权限按钮）
     */
    private Integer menuType;
    /**
     * 1显示 2禁用
     */
    private String permsType;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 跳转路径
     */
    private String url;
    /**
     * 是否是叶子节点：1：是 0：不是
     */
    private Boolean isLeaf;
    /**
     * 排序
     */
    private Double sortNo;
    /**
     * 删除状态
     */
    private Integer delFlag;

    private List<MenuTreeModel> child;

    public MenuTreeModel(Permission permission) {
        this.id = permission.getId();
        this.name = permission.getName();
        this.parentId = permission.getParentId();
        this.perms = permission.getPerms();
        this.menuType = permission.getMenuType();
        this.permsType = permission.getPermsType();
        this.icon = permission.getIcon();
        this.url = permission.getUrl();
        this.sortNo = permission.getSortNo();
        this.delFlag = permission.getDelFlag();
        this.isLeaf = permission.getIsLeaf();
        if (!permission.getIsLeaf()) {
            this.child = new ArrayList<>();
        }
    }
}
