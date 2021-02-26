package dckj.arrange.common.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Classname ColumnUtil
 * @Description TODO
 * @Date 2019/8/30 10:20
 * @Created by JinPeng
 * @Version 1.0
 */
public class ColumnUtil {

    public static String[] getColumnValue(Class classz, Annotation annotation) {
        Field[] fields=classz.getDeclaredFields();
        Field field;
        String[] value=new String[fields.length];
        for (int i = 0; i <fields.length ; i++) {
            fields[i].setAccessible(true);
        }
        for(int i = 1;i<fields.length ; i++){
            try {
                field=classz.getDeclaredField(fields[i].getName());
                Annotation column = field.getAnnotation(Annotation.class); //获取指定类型注解
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return value;
    }


//    public static void main(String[] args) {
//        String obj = getAnnotationValue("name", new BaseImportDto(), "chineseNum").toString();
//        System.out.println(obj);
//    }

    //获取该注解对象的属性值
    public static Object getAnnotationValue( String property, Object o, String column) {
        Object result = null;
            Map map = (Map) getFieldValue(o, column);
            if (map != null) {
                result = map.get(property);
            }
        return result;
    }

    public static <T> Object getFieldValue(T object, String property) {
        if (object != null && property != null) {
            Class<T> currClass = (Class<T>) object.getClass();

            try {
                Field field = currClass.getDeclaredField(property);
                field.setAccessible(true);
                return field.get(object);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(currClass + " has no property: " + property);
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
