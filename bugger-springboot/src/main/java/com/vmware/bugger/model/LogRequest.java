package com.vmware.bugger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * Created by okaplan on 23/12/15.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LogRequest {
    @JsonProperty("AleryType")
    Integer AleryType;
    @JsonProperty("AlertName")
    String AlertName;
    @JsonProperty("SearchPeriod")
    Integer SearchPeriod;
    @JsonProperty("HitCount")
    Double HitCount;
    @JsonProperty("HitOperator")
    Integer HitOperator;
    @JsonProperty("Messages")
    List<Message> Messages;
    @JsonProperty("HasMoreResults")
    Boolean HasMoreResults;
    @JsonProperty("Url")
    String Url;
    @JsonProperty("EditUrl")
    String EditUrl;
    @JsonProperty("Info")
    String Info;
    @JsonProperty("NumHits")
    Integer NumHits;

    public Integer getAleryType() {
        return AleryType;
    }

    public void setAleryType(Integer aleryType) {
        AleryType = aleryType;
    }

    public String getAlertName() {
        return AlertName;
    }

    public void setAlertName(String alertName) {
        AlertName = alertName;
    }

    public Integer getSearchPeriod() {
        return SearchPeriod;
    }

    public void setSearchPeriod(Integer searchPeriod) {
        SearchPeriod = searchPeriod;
    }

    public Double getHitCount() {
        return HitCount;
    }

    public void setHitCount(Double hitCount) {
        HitCount = hitCount;
    }

    public Integer getHitOperator() {
        return HitOperator;
    }

    public void setHitOperator(Integer hitOperator) {
        HitOperator = hitOperator;
    }

    public List<Message> getMessages() {
        return Messages;
    }

    public void setMessages(List<Message> messages) {
        Messages = messages;
    }

    public Boolean getHasMoreResults() {
        return HasMoreResults;
    }

    public void setHasMoreResults(Boolean hasMoreResults) {
        HasMoreResults = hasMoreResults;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getEditUrl() {
        return EditUrl;
    }

    public void setEditUrl(String editUrl) {
        EditUrl = editUrl;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public Integer getNumHits() {
        return NumHits;
    }

    public void setNumHits(Integer numHits) {
        NumHits = numHits;
    }
}
