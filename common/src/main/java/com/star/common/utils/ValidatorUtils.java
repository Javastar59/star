package com.star.common.utils;


import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 实体验证工具类
 */
public class ValidatorUtils {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static final String DELIMITER_COMMA = ",";

    /**
     * 验证并且在不通过时抛出异常消息
     *
     * @param t
     * @param groups
     * @param <T>
     */
    public static <T> void validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validateResult = validator.validate(t, groups);
        String message = generalMethod(validateResult);
        Optional.ofNullable(message).orElseThrow(RuntimeException::new);
    }

    /**
     * Collection 验证并且在不通过时抛出异常消息
     *
     * @param collection
     * @param groups
     * @param <T>
     */
    public static <T> void validateCollection(Collection<T> collection, Class... groups) {
        StringBuffer message = new StringBuffer();
        Validator validator = validatorFactory.getValidator();
        collection.forEach(e -> {
            Set<ConstraintViolation<T>> validateResult = validator.validate(e, groups);
            if (!validateResult.isEmpty()) {
                message.append(generalMethod(validateResult));
            }
        });
        Optional.ofNullable(message).orElseThrow(RuntimeException::new);
    }

    /**
     * 通用validateResult处理方法
     *
     * @param validateResult
     * @param <T>
     * @return
     */
    private static <T> String generalMethod(Set<ConstraintViolation<T>> validateResult) {
        if (!validateResult.isEmpty()) {
            List errorList = new ArrayList<String>();
            validateResult.forEach(e -> errorList.add(e.getPropertyPath().toString() + ":" + e.getMessage()));
            return StringUtils.join(errorList, DELIMITER_COMMA) + ".";
        }
        return null;
    }

    /**
     * 获取验证结果set
     *
     * @param t
     * @param groups
     * @param <T>
     */
    public static <T> Set<ConstraintViolation<T>> getValidateResult(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        return validator.validate(t, groups);
    }

    /**
     * 获取 Validator
     *
     * @param <T>
     */
    public static <T> Validator getValidator() {
        return validatorFactory.getValidator();
    }

}
