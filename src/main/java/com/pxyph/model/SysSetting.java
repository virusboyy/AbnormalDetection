package com.pxyph.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class SysSetting  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;//系统配置id
    private String input_type;//输入类型
    private String video_id; //视频id
    private String video;//视频文件名
    private MultipartFile videofile; //视频文件
    private String save_path;//存储路径
    private String weights; //模型权重
    private MultipartFile weightsFile;//权重文件
    private String model;//模型文件名
    private MultipartFile modelfile;//模型文件
    private Integer play_set;//播放设置
    private String username;//用于确定由哪个用户设定的


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

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
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

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public MultipartFile getWeightsFile() {
        return weightsFile;
    }

    public void setWeightsFile(MultipartFile weightsFile) {
        this.weightsFile = weightsFile;
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


    @Override
    public String toString() {
        return "SysSetting{" +
                "id=" + id +
                ", input_type='" + input_type + '\'' +
                ", video_id='" + video_id + '\'' +
                ", video='" + video + '\'' +
                ", videofile=" + videofile +
                ", save_path='" + save_path + '\'' +
                ", weights='" + weights + '\'' +
                ", weightsFile=" + weightsFile +
                ", model='" + model + '\'' +
                ", modelfile=" + modelfile +
                ", play_set=" + play_set +
                ", username='" + username + '\'' +
                '}';
    }
}
