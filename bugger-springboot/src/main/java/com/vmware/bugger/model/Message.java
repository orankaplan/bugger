package com.vmware.bugger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * Created by okaplan on 23/12/15.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Message {
    @JsonProperty("text")
    String text;
    @JsonProperty("timestamp")
    String timestamp;
    @JsonProperty("Fields")
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
