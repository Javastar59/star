package com.star.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * 映射工具
 */
public class MapperUtils {

    /**
     * 属性复制
     *
     * @param a          A 原始数据
     * @param b          B 目标数据
     * @param biConsumer BiConsumer<A,B> 自定义转换器
     * @param <A>        A 数据A类型
     * @param <B>        B 数据B类型
     * @return B 目标数据
     */
    public static <A, B> B map(A a, B b, BiConsumer<A, B> biConsumer) {
        BeanUtils.copyProperties(a, b);
        if (Objects.nonNull(biConsumer)) {
            biConsumer.accept(a, b);
        }
        return b;
    }

    /**
     * 空转换
     *
     * @param <A>
     * @param <B>
     * @return
     */
    public static <A, B> BiConsumer<A, B> empty() {
        return (A a, B b) -> {
        };
    }

}
