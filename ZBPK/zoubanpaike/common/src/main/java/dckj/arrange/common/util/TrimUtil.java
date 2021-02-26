package dckj.arrange.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class TrimUtil {
    /**
     * 去掉bean中所有属性为字符串的前后空格
     *
     * @param bean
     * @throws Exception
     */
    public static void beanAttributeValueTrim(Object bean) {
        if (bean != null) {
            //获取所有的字段包括public,private,protected,private
            Field[] fields = bean.getClass().getDeclaredFields();
            Field[] declaredFields = bean.getClass().getSuperclass().getDeclaredFields();
            fields = ArrayUtils.addAll(fields, declaredFields);
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                if (f.getType().getName().equals("java.lang.String")) {
                    //获取字段名
                    String key = f.getName();
                    Object value = getFieldValue(bean, key);
                    if (value == null) {
                        continue;
                    }
                    setFieldValue(bean, key, value.toString().replace(" ", ""));
                }
            }
        }
    }

    /**
     * 利用反射通过get方法获取bean中字段fieldName的值
     *
     * @param bean
     * @param fieldName
     * @return
     * @throws Exception
     */
    private static Object getFieldValue(Object bean, String fieldName) {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("get")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        Object rObject = null;
        Method method = null;

        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[0];
        try {
            method = bean.getClass().getMethod(methodName, classArr);
            rObject = method.invoke(bean, new Object[0]);
        } catch (NoSuchMethodException e) {
            log.info("【去空格方法出错】"+e.getMessage());
        } catch (IllegalAccessException e) {
            log.info("【去空格方法出错】"+e.getMessage());
        } catch (InvocationTargetException e) {
            log.info("【去空格方法出错】"+e.getMessage());
        }


        return rObject;
    }

    /**
     * 利用发射调用bean.set方法将value设置到字段
     *
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    private static void setFieldValue(Object bean, String fieldName, Object value) {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("set")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        /**
         * 利用发射调用bean.set方法将value设置到字段
         */
        Class[] classArr = new Class[1];
        classArr[0] = "java.lang.String".getClass();
        Method method = null;
        try {
            method = bean.getClass().getMethod(methodName, classArr);
            method.invoke(bean, value);
        } catch (NoSuchMethodException e) {
            log.info("【去空格方法出错】"+e.getMessage());
        } catch (IllegalAccessException e) {
            log.info("【去空格方法出错】"+e.getMessage());
        } catch (InvocationTargetException e) {
            log.info("【去空格方法出错】"+e.getMessage());
        }

    }

//    public static void main(String[] args) {
//        TeacherInfoVo teacherInfoVo = new TeacherInfoVo();
//        teacherInfoVo.setSchoolCode("000 07");
//        beanAttributeValueTrim(teacherInfoVo);
//        System.out.println(teacherInfoVo.getSchoolCode());
//    }
}
