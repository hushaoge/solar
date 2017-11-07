package com.solar.core.orm.db;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by hushaoge on 2016/11/17.
 */
public class MySQLDialect implements Dialect{

    public MySQLDialect() {
    }

    @Override
    public String getPaggingString(String sql, int offset, int limit) {
        return this.getLimitString(sql, offset, Integer.toString(offset), Integer.toString(limit));
    }


    public String getLimitString(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append(" limit ");
        if(offset > 0) {
            stringBuilder.append(offsetPlaceholder).append(",").append(limitPlaceholder);
        } else {
            stringBuilder.append(limitPlaceholder);
        }

        return stringBuilder.toString();
    }

    @Override
    public String getOrderString(String sql, String orderColumns, OrderType orderType) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        String type;
        if(orderType != null && orderType != OrderType.ASC) {
            type = "desc";
        } else {
            type = "asc";
        }

        stringBuilder.append(" order by ");
        if(StringUtils.isNotBlank(orderColumns)) {
            stringBuilder.append(orderColumns).append(" ").append(type);
        }

        return stringBuilder.toString();
    }

    @Override
    public String getCompPaggingString(String sql, int offset, int limit) {
        sql = sql.trim();
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        // pagingSelect.append("( ");
        pagingSelect.append(sql);
        // pagingSelect.append(" ) " );
        pagingSelect.append("limit ");
        pagingSelect.append(limit);
        pagingSelect.append(" offset ");
        pagingSelect.append(offset);
        return pagingSelect.toString();
    }

}
