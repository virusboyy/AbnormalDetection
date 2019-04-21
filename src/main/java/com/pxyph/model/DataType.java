package com.pxyph.model;

import com.pxyph.util.tag.PageModel;

import java.io.Serializable;
import java.util.List;

public class DataType implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<SysSetting> sysSettings;
    private PageModel pageModel;

    public DataType() {
        super();
    }

    public List<SysSetting> getSysSettings() {
        return sysSettings;
    }

    public void setSysSettings(List<SysSetting> sysSettings) {
        this.sysSettings = sysSettings;
    }

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    @Override
    public String toString() {
        return "DataType{" +
                "sysSettings=" + sysSettings +
                ", pageModel=" + pageModel +
                '}';
    }
}
