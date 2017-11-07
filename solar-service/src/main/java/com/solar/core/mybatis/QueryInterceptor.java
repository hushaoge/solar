package com.solar.core.mybatis;

import com.solar.core.orm.db.Dialect;
import com.solar.core.utils.ReflectionUtils;
import com.solar.core.utils.SQLHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * Created by hushaoge on 2016/11/17.
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
)})
public class QueryInterceptor implements Interceptor, Serializable {
    private static final long serialVersionUID = 7462150664602937384L;
    public  Dialect dialect ;
    protected static final String _SQL_PATTERN = ".*[qQ]uery.*||.*Page$";
    private static Log log = LogFactory.getLog(QueryInterceptor.class);
    public QueryInterceptor(){

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof RoutingStatementHandler) {

            RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectionUtils.getFieldValue("delegate",statementHandler);
            MappedStatement mappedStatement = (MappedStatement)ReflectionUtils.getSuperFieldValue("mappedStatement", delegate);
            if(mappedStatement.getId().matches(this._SQL_PATTERN)) {
                BoundSql boundSql = delegate.getBoundSql();
                String originalSql = boundSql.getSql();
                if(StringUtils.isBlank(originalSql)) {
                    return invocation.proceed();
                }

                Map parameterObject = (Map)boundSql.getParameterObject();
                Pager pager = SQLHelper.getPager(parameterObject);
                if(pager != null) {
                    SQLHelper.initPagination(pager);
                    originalSql = SQLHelper.generateOrderSql(originalSql, pager, this.dialect);
                    String pageSql = SQLHelper.generatePageSql(originalSql, pager, this.dialect);
                    ReflectionUtils.setFieldValue("sql", pageSql, boundSql);
                    log.debug("分页sql："+pageSql);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
    // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }
}
