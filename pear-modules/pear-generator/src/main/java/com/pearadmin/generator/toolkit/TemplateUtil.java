package com.pearadmin.generator.toolkit;

import com.pearadmin.generator.domain.FieldInfo;

import java.util.List;

/**
 * 处理模板变量常用方法工具类
 *
 * @author Bamboo
 * @since 2020-09-27
 */
public class TemplateUtil {

    /**
     * 首字母小写
     *
     * @param source value
     * @return String with first letter lowercase
     */
    public static String lowerFirst(String source) {
        if(Character.isLowerCase(source.charAt(0))) {
            return source;
        }
        return Character.toLowerCase(source.charAt(0)) + source.substring(1);
    }

    /**
     * 下划线转驼峰
     *
     * @param underlineName value
     * @return a camel case named string
     */
    public static String underlineToCamel(String underlineName) {
        // 截取下划线分成数组
        char[] charArray = underlineName.toCharArray();
        // 判断上次循环的字符是否是"_"
        boolean underlineBefore = false;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0, length = charArray.length; i < length; i++) {
            // 判断当前字符是否是"_",如果跳出本次循环
            if (charArray[i] == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                // 如果为true，代表上次的字符是"_",当前字符需要转成大写
                buffer.append(charArray[i] -= 32);
                underlineBefore = false;
            } else {
                // 不是"_"后的字符就直接追加
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param fieldInfoList list
     * @return a camel case named list
     */
    public static List<FieldInfo> underlineToCamel(List<FieldInfo> fieldInfoList) {
        for (FieldInfo fieldInfo : fieldInfoList) {
            String str = TemplateUtil.underlineToCamel(fieldInfo.getFieldName());
            fieldInfo.setFieldName(str);
        }
        return fieldInfoList;
    }
}
