package com.pxyph.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String event_type;
    private Date create_time;
    private String transfer_time;

    public String getLog_document() {
        return log_document;
    }

    public void setLog_document(String log_document) {
        this.log_document = log_document;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String log_document;
    private String username;




    public LogInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getCreate_time() {
        Date date = this.create_time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        transfer_time = sdf.format(date);
        return transfer_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }


    @Override
    public String toString() {
        return "LogInfo{" +
                "id=" + id +
                ", event_type='" + event_type + '\'' +
                ", create_time=" + transfer_time +
                ", log_document='" + log_document + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
