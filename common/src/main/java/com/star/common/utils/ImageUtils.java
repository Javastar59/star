package com.star.common.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片处理工具类
 */
public class ImageUtils {

    private static final Pattern pattern = Pattern.compile("[^\\.]+\\.(jpe?g|png|bmp|gif)$", Pattern.CASE_INSENSITIVE);

    public static class Base64stream2imageTransResult {
        private InputStream stream;
        private String imagePattern;
    }

    public static Base64stream2imageTransResult base64stream2image(String base64stream) {
        String[] streamArray = base64stream.split(",");
        Preconditions.checkArgument(streamArray.length == 2);
        byte[] bytes = Base64.getDecoder().decode(streamArray[1]);
        Pattern pattern = Pattern.compile("(gif|bmp|png|jpg|jpeg)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(streamArray[0]);
        boolean matched = matcher.find();
        Preconditions.checkArgument(matched, String.format("未知的图片类型: %s", matcher.group()));
        Base64stream2imageTransResult result = new Base64stream2imageTransResult();
        result.imagePattern = matcher.group();
        result.stream = new ByteArrayInputStream(bytes);
        return result;
    }

    public static String getBizImageName(String imageName) {
        if (StringUtils.isNotBlank(imageName)) {
            Matcher matcher = pattern.matcher(imageName);
            return matcher.matches() ? imageName : String.format("%s.jpg", imageName);
        } else {
            return imageName;
        }
    }

    public static List<String> getBizImageNames(List<String> imageNames) {
        List<String> bizImageNames = Lists.newArrayList();
        if (!imageNames.isEmpty()) {
            for (String imageName : imageNames) {
                if (StringUtils.isNotBlank(imageName)) {
                    bizImageNames.add(ImageUtils.getBizImageName(imageName));
                } else {
                    bizImageNames.add(imageName);
                }
            }
        }
        return bizImageNames;
    }
}
