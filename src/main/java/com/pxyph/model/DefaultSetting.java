package com.pxyph.model;

import java.io.Serializable;

/**
 * 默认设置表
 */
public class DefaultSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;//默认设置id
    private String input_type;//输入类型
    private Integer video_id;//视频id
    private String save_path;//存储路径
    private String model;//模型路径
    private Integer play_set;//播放设置
    private String event_type;
    private int start_time;
    private int end_time;

    @Override
    public String toString() {
        return "DefaultSetting{" +
                "id=" + id +
                ", input_type='" + input_type + '\'' +
                ", video_id=" + video_id +
                ", save_path='" + save_path + '\'' +
                ", model='" + model + '\'' +
                ", play_set=" + play_set +
                ", event_type='" + event_type + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }

    //无参构造
    public DefaultSetting() {
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

    public Integer getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Integer video_id) {
        this.video_id = video_id;
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

    public Integer getPlay_set() {
        return play_set;
    }

    public void setPlay_set(Integer play_set) {
        this.play_set = play_set;
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

}
