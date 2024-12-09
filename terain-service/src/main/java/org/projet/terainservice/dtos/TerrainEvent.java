package org.projet.terainservice.dtos;

import java.io.Serializable;

public class TerrainEvent implements Serializable {
    private String eventType;
    private Object data;

    public TerrainEvent(String eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
