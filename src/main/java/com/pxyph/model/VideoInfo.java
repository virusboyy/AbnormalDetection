package com.pxyph.model;

import java.io.Serializable;
import java.math.BigInteger;

public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String video_id;
    private String file_name;
    private BigInteger file_size;
    private Integer video_width;
    private Integer video_height;
    private Integer frame_num;
    private Integer fps;
    private String video_path;
    private String event_type;
    private int start_time;
    private int end_time;

    public VideoInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public BigInteger getFile_size() {
        return file_size;
    }

    public void setFile_size(BigInteger file_size) {
        this.file_size = file_size;
    }

    public Integer getVideo_width() {
        return video_width;
    }

    public void setVideo_width(Integer video_width) {
        this.video_width = video_width;
    }

    public Integer getVideo_height() {
        return video_height;
    }

    public void setVideo_height(Integer video_height) {
        this.video_height = video_height;
    }

    public Integer getFrame_num() {
        return frame_num;
    }

    public void setFrame_num(Integer frame_num) {
        this.frame_num = frame_num;
    }

    public Integer getFps() {
        return fps;
    }

    public void setFps(Integer fps) {
        this.fps = fps;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "id=" + id +
                ", video_id='" + video_id + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_size=" + file_size +
                ", video_width=" + video_width +
                ", video_height=" + video_height +
                ", frame_num=" + frame_num +
                ", fps=" + fps +
                ", video_path='" + video_path + '\'' +
                ", event_type='" + event_type + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
