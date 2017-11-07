package com.solar.entities.dtos.media;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.Objects;


/**
 * 文本
 * Created by jfliu on 2015/12/10.
 */
public class TextMediaDTO implements Serializable {

    private static final long serialVersionUID = -291062627423915562L;

    @JSONField(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TextMediaDTO other = (TextMediaDTO) obj;
        return Objects.equals(this.content, other.content);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
