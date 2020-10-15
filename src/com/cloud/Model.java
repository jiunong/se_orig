package com.cloud;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/10/15 10:47
 */
public class Model {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Model{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
