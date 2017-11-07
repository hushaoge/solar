package com.solar.core.utils;

import com.solar.core.orm.db.Dialect;
import com.solar.core.mybatis.Pager;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by hushaoge on 2016/11/17.
 */
public class SQLHelper {
    protected Log log = LogFactory.getLog(this.getClass());

    public SQLHelper() {
    }

    public static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List parameterMappings = boundSql.getParameterMappings();
        if(parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null?null:configuration.newMetaObject(parameterObject);

            for(int i = 0; i < parameterMappings.size(); ++i) {
                ParameterMapping parameterMapping = (ParameterMapping)parameterMappings.get(i);
                if(parameterMapping.getMode() != ParameterMode.OUT) {
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    Object value;
                    if(parameterObject == null) {
                        value = null;
                    } else if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if(boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if(propertyName.startsWith("__frch_") && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if(value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null?null:metaObject.getValue(propertyName);
                    }

                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if(typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }

                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }

    }

    public static int getCount(String sql, Connection connection, MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) throws SQLException {
        String countSql = "select count(1) from (" + sql + ") as tmp_count";
        PreparedStatement countStmt = null;
        ResultSet rs = null;

        int var10;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
            setParameters(countStmt, mappedStatement, countBS, parameterObject);
            rs = countStmt.executeQuery();
            int count = 0;
            if(rs.next()) {
                count = rs.getInt(1);
            }

            var10 = count;
        } finally {
            if(rs != null) {
                rs.close();
            }

            if(countStmt != null) {
                countStmt.close();
            }

            if(connection != null) {
                connection.close();
            }
        }

        return var10;
    }

    public static String generateOrderSql(String sql, Pager pager, Dialect dialect) {
        return StringUtils.isNotBlank(pager.getOrderColumns())?dialect.getOrderString(sql, pager.getOrderColumns(), pager.getOrderType()):sql;
    }

    public static Pager getPager(Map parameterObject) {
        Pager pager = null;
        if(parameterObject != null) {
            if(parameterObject.size() == 0) {
                return null;
            }

            if(parameterObject.containsKey("pager")) {
                pager = convertParameter(parameterObject.get("pager"), pager);
            } else {
                pager = convertParameter(parameterObject.get("param1"), pager);
            }
        }
        return pager;
    }

    protected static Pager convertParameter(Object parameterObject, Pager pager) {
        if(parameterObject instanceof Pager) {
            pager = (Pager)parameterObject;
        }
        return pager;
    }

    public static String generatePageSql(String sql, Pager pager, Dialect dialect) {
        int pageSize = pager.getPageSize().intValue();
        int index = (pager.getCurrentPage().intValue() - 1) * pageSize;
        int start = index < 0?0:index;
        return dialect.getPaggingString(sql, start, pageSize);
    }

    public static void initPagination(Pager pager) {
        if(pager.getTotalCount().intValue() % pager.getPageSize().intValue() == 0) {
            pager.setPageCount(Integer.valueOf(pager.getTotalCount().intValue() / pager.getPageSize().intValue()));
        } else {
            pager.setPageCount(Integer.valueOf(pager.getTotalCount().intValue() / pager.getPageSize().intValue() + 1));
        }

    }
}
