package com.solar.entities.dtos.message;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by hushaoge on 2016/11/14.
 */
public class CustomSendDTO implements Serializable {

    private static final long serialVersionUID = -4744190680273504032L;

    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(openId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CustomSendDTO other = (CustomSendDTO) obj;
        return Objects.equals(this.openId, other.openId);
    }

    @Override
    public String toString() {
        return "CustomSendDTO{" +
                "openId='" + openId + '\'' +
                '}';
    }
}
