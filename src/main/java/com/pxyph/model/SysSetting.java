package com.pxyph.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class SysSetting  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;//系统配置id
    private String input_type;//输入类型
    private String video;//视频文件名
    private MultipartFile videofile; //视频文件
    private String save_path;//存储路径
    private String model;//模型文件名
    private MultipartFile modelfile;//模型文件
    private Integer play_set;//播放设置
    private String username;//用于确定由哪个用户设定的
    private String event_type;
    private int start_time;
    private int end_time;


    public SysSetting() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInput_type() {
        return input_type;
    }

    public void setInput_type(String input_type) {
        this.input_type = input_type;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public MultipartFile getVideofile() {
        return videofile;
    }

    public void setVideofile(MultipartFile videofile) {
        this.videofile = videofile;
    }

    public String getSave_path() {
        return save_path;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public MultipartFile getModelfile() {
        return modelfile;
    }

    public void setModelfile(MultipartFile modelfile) {
        this.modelfile = modelfile;
    }

    public Integer getPlay_set() {
        return play_set;
    }

    public void setPlay_set(Integer play_set) {
        this.play_set = play_set;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "SysSetting{" +
                "id=" + id +
                ", input_type='" + input_type + '\'' +
                ", video='" + video + '\'' +
                ", videofile=" + videofile +
                ", save_path='" + save_path + '\'' +
                ", model='" + model + '\'' +
                ", modelfile=" + modelfile +
                ", play_set=" + play_set +
                ", username='" + username + '\'' +
                ", event_type='" + event_type + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
