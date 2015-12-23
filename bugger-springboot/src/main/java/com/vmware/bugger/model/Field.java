package com.vmware.bugger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by okaplan on 23/12/15.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Field {
    @JsonProperty("name")
    String name;
    @JsonProperty("content")
    String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
