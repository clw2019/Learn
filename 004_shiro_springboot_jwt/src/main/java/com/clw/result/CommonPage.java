package com.clw.result;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonPage<T> {
    private Integer pageSize;   //每页有多少条数据
    private Integer pageNum;    //当前页码
    private Integer totalPage;  //总共页数
    private Long total;         //总条数
    private List<T> list;

    private CommonPage(){
    }

    /**
     * 将PageHelper分页后的list转化为分页
     */
    public static <T> CommonPage<T> resultPage(List<T> list) {
        CommonPage<T> commonPage = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        commonPage.setPageSize(pageInfo.getPageSize());
        commonPage.setPageNum(pageInfo.getPageNum());
        commonPage.setTotalPage(pageInfo.getPages());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setList(pageInfo.getList());
        return commonPage;
    }
}
