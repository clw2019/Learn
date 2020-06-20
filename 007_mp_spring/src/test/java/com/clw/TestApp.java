package com.clw;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clw.domain.User;
import com.clw.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/19 23:59
 */
public class TestApp {

    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
    UserMapper userMapper = context.getBean(UserMapper.class);

    /**
     * 添加,
     * 设置了全局配置主键自增之后,添加的时候id值就无用了
     */
    @Test
    public void test01() {
        userMapper.insert(new User(115, "小明", 245, "@Test111"));
        System.out.println("操作成功");
    }

    /**
     * 修改
     */
    @Test
    public void test02() {
        userMapper.updateById(new User(116, "大明", 26, "@Test222"));
        System.out.println("updateById success...");
    }

    /***
     * 修改:条件构造器
     */
    @Test
    public void test03() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>( );
        updateWrapper.eq("name", "小明"); // where name = '小明';
        //updateWrapper.or().eq("name", "大明明");
        userMapper.update(new User(112, "大明明", 18, "@Test123456"), updateWrapper);
        System.out.println("UpdateWrapper success...");
    }

    /**
     * 删除
     */
    @Test
    public void test04() {
        userMapper.deleteById(112);
        System.out.println("deleteById success...");
        List<Integer> list = new ArrayList<Integer>();
        list.add(111);
        list.add(115);
        userMapper.deleteBatchIds(list);
        System.out.println("deleteBatchIds success...");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 3);
        map.put("name", "Tom");  // where id = 3 and name = 'Tom'
        userMapper.deleteByMap(map);  //只能是 and 关系
        System.out.println("deleteByMap success...");

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.between("id", 5, 116); //可以是 and 也可是 or
        userMapper.delete(queryWrapper);
    }

    /**
     * 查询
     */
    @Test
    public void test05() {
        /**
         * 根据 ID 查询
         */
        System.out.println("通过id查找 : " + userMapper.selectById(1));

        /**
         * 查询（根据ID 批量查询）,
         * 返回 List<T>
         */
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        System.out.println("批量查询 : " + userMapper.selectBatchIds(list));

        /**
         * 查询（根据 columnMap 条件）
         * 返回 List<T>
         */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "小明");
        map.put("age", 24);
        System.out.println("selectByMap : " + userMapper.selectByMap(map));

        /**
         * 根据 Wrapper 条件，查询总记录数
         */
        System.out.println("selectCount : " + userMapper.selectCount(new QueryWrapper<User>()));

        /**
         * 据 entity 条件，查询全部记录,
         * 返回 List<T>
         */
        System.out.println("selectList : " + userMapper.selectList(new QueryWrapper<User>()));

        /**
         * 根据 Wrapper 条件，查询全部记录,
         * 返回 List<Map<String, Object>>
         */
        System.out.println("selectMaps : " + userMapper.selectMaps(new QueryWrapper<User>()));

        /**
         * 根据 entity 条件，查询全部记录（并翻页）,
         * 返回 List<T>
         */
        System.out.println("selectPage : " + userMapper.selectPage(new Page<User>(1,2), new QueryWrapper<User>()).getRecords());

        /**
         * 根据 Wrapper 条件，查询全部记录（并翻页）,
         * 返回 List<Map<String, Object>>
         */
        System.out.println("selectMapsPage : " + userMapper.selectMapsPage(new Page<Map<String, Object>>(1, 2), new QueryWrapper<User>()).getRecords());

        /**
         * 根据 entity 条件，查询一条记录
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", "Jack");
        System.out.println("selectOne : " + userMapper.selectOne(queryWrapper));

        /**
         * 根据 Wrapper 条件，查询全部记录,返回第一个字段的值
         */
        System.out.println("selectObjs : " + userMapper.selectObjs(queryWrapper));
    }

    /**
     * 测试xxMapper.xml
     */
    @Test
    public void test07() {
        List<User> userList = userMapper.selectAll();
        System.out.println("自定义方法 : " + userList);
    }
}