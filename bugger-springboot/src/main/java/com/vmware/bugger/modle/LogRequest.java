package com.vmware.bugger.modle;

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
}
