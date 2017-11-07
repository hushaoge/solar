package com.solar.entities.dtos.message;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by hushaoge on 2016/11/14.
 */
public class KFMessageDTO implements Serializable {

    private static final long serialVersionUID = -7065829225235646537L;

    private CustomSendDTO customSendDTO;

    public CustomSendDTO getCustomSendDTO() {
        return customSendDTO;
    }

    public void setCustomSendDTO(CustomSendDTO customSendDTO) {
        this.customSendDTO = customSendDTO;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customSendDTO);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final KFMessageDTO other = (KFMessageDTO) obj;
        return Objects.equals(this.customSendDTO, other.customSendDTO);
    }

    @Override
    public String toString() {
        return "KFMessageDTO{" +
                "customSendDTO=" + customSendDTO +
                '}';
    }
}
