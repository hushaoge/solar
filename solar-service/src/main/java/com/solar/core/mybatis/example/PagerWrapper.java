package com.solar.core.mybatis.example;

import com.solar.core.mybatis.Pager;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;

import java.util.List;

/**
 * Created by hushaoge on 2016/11/18.
 */
public class PagerWrapper extends BaseWrapper{

    private Pager pager;

    public PagerWrapper(MetaObject metaObject, Pager<Object> pager) {
        super(metaObject);
        this.pager = pager;
    }

    @Override
    public Object get(PropertyTokenizer propertyTokenizer) {
        return null;
    }

    @Override
    public void set(PropertyTokenizer propertyTokenizer, Object o) {

    }

    @Override
    public String findProperty(String s, boolean b) {
        return null;
    }

    @Override
    public String[] getGetterNames() {
        return new String[0];
    }

    @Override
    public String[] getSetterNames() {
        return new String[0];
    }

    @Override
    public Class<?> getSetterType(String s) {
        return null;
    }

    @Override
    public Class<?> getGetterType(String s) {
        return null;
    }

    @Override
    public boolean hasSetter(String s) {
        return false;
    }

    @Override
    public boolean hasGetter(String s) {
        return false;
    }

    @Override
    public MetaObject instantiatePropertyValue(String s, PropertyTokenizer propertyTokenizer, ObjectFactory objectFactory) {
        return null;
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public void add(Object o) {
        this.pager.getList().add(o);
    }

    @Override
    public <E> void addAll(List<E> list) {
        this.pager.getList().addAll(list);
    }
}
