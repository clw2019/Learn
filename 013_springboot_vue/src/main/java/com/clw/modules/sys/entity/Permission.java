package com.clw.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.clw.modules.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 菜单权限表
    */
@ApiModel(value="com-clw-modules-sys-entity-Permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_permission")
public class Permission extends BaseEntity {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键id")
    private String id;

    /**
     * 父id
     */
    @TableField(value = "parent_id")
    @ApiModelProperty(value="父id")
    private String parentId;

    /**
     * 菜单标题
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="菜单标题")
    private String name;

    /**
     * 路径
     */
    @TableField(value = "url")
    @ApiModelProperty(value="路径")
    private String url;

    /**
     * 组件
     */
    @TableField(value = "component")
    @ApiModelProperty(value="组件")
    private String component;

    /**
     * 组件名字
     */
    @TableField(value = "component_name")
    @ApiModelProperty(value="组件名字")
    private String componentName;

    /**
     * 一级菜单跳转地址
     */
    @TableField(value = "redirect")
    @ApiModelProperty(value="一级菜单跳转地址")
    private String redirect;

    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
    @TableField(value = "menu_type")
    @ApiModelProperty(value="菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)")
    private Integer menuType;

    /**
     * 菜单权限编码
     */
    @TableField(value = "perms")
    @ApiModelProperty(value="菜单权限编码")
    private String perms;

    /**
     * 权限策略1显示2禁用
     */
    @TableField(value = "perms_type")
    @ApiModelProperty(value="权限策略1显示2禁用")
    private String permsType;

    /**
     * 菜单排序
     */
    @TableField(value = "sort_no")
    @ApiModelProperty(value="菜单排序")
    private Double sortNo;

    /**
     * 聚合子路由: 1是0否
     */
    @TableField(value = "always_show")
    @ApiModelProperty(value="聚合子路由: 1是0否")
    private Boolean alwaysShow;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value="菜单图标")
    private String icon;

    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    @TableField(value = "is_route")
    @ApiModelProperty(value="是否路由菜单: 0:不是  1:是（默认值1）")
    private Boolean isRoute;

    /**
     * 是否叶子节点:    1:是   0:不是
     */
    @TableField(value = "is_leaf")
    @ApiModelProperty(value="是否叶子节点:    1:是   0:不是")
    private Boolean isLeaf;

    /**
     * 是否缓存该页面:    1:是   0:不是
     */
    @TableField(value = "keep_alive")
    @ApiModelProperty(value="是否缓存该页面:    1:是   0:不是")
    private Boolean keepAlive;

    /**
     * 是否隐藏路由: 0否,1是
     */
    @TableField(value = "hidden")
    @ApiModelProperty(value="是否隐藏路由: 0否,1是")
    private Integer hidden;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value="描述")
    private String description;

    /**
     * 删除状态 0正常 1已删除
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="删除状态 0正常 1已删除")
    private Integer delFlag;

    /**
     * 是否添加数据权限1是0否
     */
    @TableField(value = "rule_flag")
    @ApiModelProperty(value="是否添加数据权限1是0否")
    private Integer ruleFlag;

    /**
     * 按钮权限状态(0无效1有效)
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="按钮权限状态(0无效1有效)")
    private String status;

    /**
     * 外链菜单打开方式 0/内部打开 1/外部打开
     */
    @TableField(value = "internal_or_external")
    @ApiModelProperty(value="外链菜单打开方式 0/内部打开 1/外部打开")
    private Boolean internalOrExternal;

}