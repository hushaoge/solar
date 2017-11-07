package com.solar.core.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by hushaoge on 2016/11/17.
 */
public class ReflectionUtils extends PropertyUtils {

    /**
     * 强制获取属性
     * @param propertyName
     * @param obj
     * @return
     */
    public static Object getFieldValue(String propertyName, Object obj) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * 强制获取父类属性
     * @param propertyName
     * @param obj
     * @return
     */
    public static Object getSuperFieldValue(String propertyName, Object obj){
        try {
            Field field = obj.getClass().getSuperclass().getDeclaredField(propertyName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
    /**
     * 强制获取属性
     * @param propertyName
     * @param obj
     * @return
     */
    public static Object getProperty(String propertyName, Object obj) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 强制设置属性
     * @param propertyName
     * @param obj
     * @return
     */
    public static void setFieldValue(String propertyName,String propertyValue, Object obj) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj, propertyValue);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}