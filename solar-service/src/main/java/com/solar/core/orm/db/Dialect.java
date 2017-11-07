package com.solar.core.orm.db;

/**
 * Created by hushaoge on 2016/11/17.
 */
public interface Dialect {

    /**
     * 获取排序的sql
     * @param sql
     * @param orderColumns
     * @param orderType
     * @return
     */
    String getOrderString(String sql, String orderColumns, OrderType orderType);

    /**
     * 分页sql
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    String getPaggingString(String sql, int offset, int limit);

    /**
     * 兼容的分页写法
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    String getCompPaggingString(String sql, int offset, int limit);
}
