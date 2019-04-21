package com.pxyph.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String video_id;
    private String file_name;
    private MultipartFile videofile; //视频文件
    private BigInteger file_size;
    private Integer video_width;
    private Integer video_height;
    private Date create_time;
    private String transfer_time;
    private Integer frame_num;
    private Integer fps;
    private String username;

    public StorageManager() {
        super();
    }

    @Override
    public String toString() {
        return "StorageManager{" +
                "id=" + id +
                ", video_id='" + video_id + '\'' +
                ", file_name='" + file_name + '\'' +
                ", videofile=" + videofile +
                ", file_size=" + file_size +
                ", video_width=" + video_width +
                ", video_height=" + video_height +
                ", create_time=" + transfer_time +
                ", frame_num=" + frame_num +
                ", fps=" + fps +
                ", username='" + username + '\'' +
                '}';
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

    public MultipartFile getVideofile() {
        return videofile;
    }

    public void setVideofile(MultipartFile videofile) {
        this.videofile = videofile;
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

    public String getCreate_time() {
        Date date = this.create_time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        transfer_time = sdf.format(date);
        return transfer_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
