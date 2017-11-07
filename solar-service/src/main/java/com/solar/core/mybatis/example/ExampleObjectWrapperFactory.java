package com.solar.core.mybatis.example;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;

/**
 * Created by hushaoge on 2016/11/18.
 */
public class ExampleObjectWrapperFactory extends DefaultObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return true;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {

        return super.getWrapperFor(metaObject, object);
    }
}
