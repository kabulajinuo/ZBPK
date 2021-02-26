package dckj.arrange.common.util;

import dckj.arrange.common.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName EmptyUtil
 * @Description 判空工具类
 * @Author Pengjin
 * @Date 2019/1/4 10:26
 * @Version 1.0
 **/
@Slf4j
public class EmptyUtil {

    /**
     * 判断字符串是否为空
     * PS:
     * 为空的条件：
     * 1. String对象为空
     * 2. 没有任何字符的字符串
     *
     * @param str 需要判断的字符串
     * @return 为空(true), 非空(false)
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 判断字符串是否为空
     * PS:
     * 为空的条件：
     * 1. String对象为空
     * 2. 没有任何字符的字符串
     *
     * @param str       需要判断的字符串
     * @param isTrimmed 判断前是否去掉字符串前后的空格：是(true), 否(false)
     * @return 为空(true), 非空(false)
     */
    public static boolean isEmpty(String str, boolean isTrimmed) {
        return isTrimmed ? null == str || "".equals(str.trim()) : null == str || "".equals(str);
    }

    /**
     * 判断对象是否为空
     *
     * @param obj 需要进行判断的对象
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }

    /**
     * 判断集合是否为空
     * PS：
     * 集合为空的条件：
     * 1. 集合对象为null
     * 2. 集合中没有元素
     *
     * @param collection 需要进行判断的集合
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.size() == 0;
    }

    /**
     * 判断对象数组是否为空
     * PS：
     * 对象数组为空的条件：
     * 1. 对象数组为null
     * 2. 对象数组中没有元素
     *
     * @param array 需要进行判断的对象数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(Object[] array) {
        return null == array || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否为空
     * PS：
     * 数组为空的条件：
     * 1. 数组为null
     * 2. 数组中没有元素
     *
     * @param array 需要进行判断的数组
     * @return 为空(true), 不为空(false)
     */
    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public static <T> void validate(List<T> list) {
        Validator validator = createValidator();
        if (!isEmpty(list)) {
            for (T t : list) {
                Set<ConstraintViolation<T>> validates = validator.validate(t);
                //判断当前数据 是否有校验不通过
                if (!isEmpty(validates)) {
                    for (ConstraintViolation<T> validate : validates) {
                        log.error(validate.getPropertyPath() + ": " + validate.getMessage());
                        throw  new CheckedException(validate.getMessage());
                    }
                }
            }
        }
    }

    /**
     * 创建校验对象
     *
     * @return
     */
    private static Validator createValidator() {
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        factory.close();
        return validator;
    }
//    public static void main(String[] args) {
//        System.out.println("是正确格式的手机号:"+isMobile("13387370598"));
//    }
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2="^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";// 验证手机号
        p = Pattern.compile(s2);
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

}
