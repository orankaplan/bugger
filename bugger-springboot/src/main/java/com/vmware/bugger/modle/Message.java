package com.vmware.bugger.modle;

import java.util.List;

/**
 * Created by okaplan on 23/12/15.
 */
public class Message {
    String text;
    String timestamp;
    List<Field> fields;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
