package com.solar.core.mybatis.example;

import com.solar.core.mybatis.Pager;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

/**
 * Created by hushaoge on 2016/11/18.
 */
public class ExampleObjectFactory extends DefaultObjectFactory {
    public ExampleObjectFactory() {
        super();
    }

    @Override
    public <T> T create(Class<T> type) {
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    @Override
    protected Class<?> resolveInterface(Class<?> type) {
        return super.resolveInterface(type);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        if(Pager.class.isAssignableFrom(type)){
            return true;
        } else {
            return super.isCollection(type);
        }
    }
}
