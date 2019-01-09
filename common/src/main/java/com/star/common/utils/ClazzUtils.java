package com.star.common.utils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射工具
 */
public final class ClazzUtils {

    public static boolean isUserClass(Class<?> clazz) {
        return clazz.getClassLoader() != null;
    }

    /**
     * 获取属性字段，即非 static, final 修饰的字段
     */
    public static List<Field> getDeclaredPropertyFields(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException();

        Field[] fields = clazz.getDeclaredFields();

        return Arrays.stream(fields)
                .filter(ClazzUtils::isPropertyField)
                .collect(Collectors.toList());
    }

    private static boolean isPropertyField(Field f) {
        return !Modifier.isFinal(f.getModifiers()) && !Modifier.isStatic(f.getModifiers());
    }

    public static Class<?> getFirstGenericParam(Class<?> clazz) {

        Type typeClass1 = clazz.getGenericSuperclass();
        if (typeClass1 instanceof ParameterizedType) {
            Type actualType1 = ((ParameterizedType) typeClass1).getActualTypeArguments()[0];
            return (Class<?>) actualType1;
        } else {
            throw new UnsupportedOperationException("没有泛型参数，不能使用此方法: clazz:" + clazz.getName());
        }
    }

    /**
     * 实例化对象
     *
     * @param clazz class对象，必须包含空构造器
     * @param <T>   类型
     * @return 实例
     */
    public static <T> T instance(Class<T> clazz) {

        Constructor<T> noArgConstructor;
        try {
            noArgConstructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("没有控构造器：" + clazz.getCanonicalName());
        }

        try {
            return noArgConstructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("实例化transformer 发生错误：" + clazz.getCanonicalName());
        }
    }

}
