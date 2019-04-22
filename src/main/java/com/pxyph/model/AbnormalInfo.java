package com.pxyph.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbnormalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String event_type;
    private Date create_time;
    private String transfer_time;
    private String anomaly_document;
    private BigInteger start_time;
    private BigInteger end_time;
    private String video_id;
    private String video_name;
    private String video_path;
    private String username;

    public AbnormalInfo() {
        super();
    }


    @Override
    public String toString() {
        return "AbnormalInfo{" +
                "id=" + id +
                ", event_type='" + event_type + '\'' +
                ", create_time=" + transfer_time +
                ", anomaly_document='" + anomaly_document + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", video_id='" + video_id + '\'' +
                ", video_name='" + video_name + '\'' +
                ", video_path='" + video_path + '\'' +
                ", username='" + username + '\'' +
                '}';
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

    public String getAnomaly_document() {
        return anomaly_document;
    }

    public void setAnomaly_document(String anomaly_document) {
        this.anomaly_document = anomaly_document;
    }

    public BigInteger getStart_time() {
        return start_time;
    }

    public void setStart_time(BigInteger start_time) {
        this.start_time = start_time;
    }

    public BigInteger getEnd_time() {
        return end_time;
    }

    public void setEnd_time(BigInteger end_time) {
        this.end_time = end_time;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
