package com.clw.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/4/21 21:47
 */
public class TestLambda {
    /**
     * Java8 内置的四大核心函数式接口
     * <p>
     * Consumer<T> : 消费型接口
     * void accept(T t);
     * <p>
     * Supplier<T> : 供给型接口
     * T get();
     * <p>
     * Function<T, R> : 函数型接口
     * R apply(T t);
     * <p>
     * Predicate<T> : 断言型接口
     * Boolean test(T t);
     */

    // Consumer<T> : 消费型接口
    @Test
    public void test01() {
        happy(1000.00, aDouble -> System.out.println("周日出去玩，花费：" + aDouble + "元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    // Supplier<T> : 供给型接口
    @Test
    public void test02() {
        List<Integer> numList = getNumList(3, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }

    // 需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    // Function<T, R> 函数型接口
    @Test
    public void test03() {
        String s = handleStr("\t\t\t\t\t 断剑重铸之日，骑士归来之时   ", fun -> fun.trim());
        System.out.println(s);
    }

    // 需求：用于处理字符串
    public String handleStr(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // Predicate<T> 断言型接口
    @Test
    public void test04() {
        List<String> list = Arrays.asList("Hello", "world", "Lambda", "a", "bb", "cbv");
        List<String> newStr = filterStr(list, str -> str.length() > 3);
        System.out.println(newStr);
    }

    // 需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }

}
