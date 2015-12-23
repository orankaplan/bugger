package com.vmware.bugger.model;

import java.util.List;

/**
 * Created by okaplan on 23/12/15.
 */
public class LogRequest {
    Integer AleryType;
    String AlertName;
    Integer SearchPeriod;
    Double HitCount;
    Integer HitOperator;
    List<Message> Messages;
    Boolean HasMoreResults;
    String Url;
    String EditUrl;
    String Info;
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
