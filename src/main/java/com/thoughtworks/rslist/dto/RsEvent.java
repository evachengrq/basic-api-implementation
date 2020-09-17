package com.thoughtworks.rslist.dto;

import javax.validation.constraints.NotEmpty;

public class RsEvent {
    @NotEmpty
    private String eventName;
    @NotEmpty
    private String keywords;

    public RsEvent(String eventName, String keywords) {
        this.eventName = eventName;
        this.keywords = keywords;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}