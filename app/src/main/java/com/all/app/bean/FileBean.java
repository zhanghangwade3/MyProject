package com.all.app.bean;

import com.all.app.base.BeanBase;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class FileBean extends BeanBase {
    String file_id;
    String file_name;
    String save_name;

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getSave_name() {
        return save_name;
    }

    public void setSave_name(String save_name) {
        this.save_name = save_name;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "file_id='" + file_id + '\'' +
                ", file_name='" + file_name + '\'' +
                ", save_name='" + save_name + '\'' +
                '}';
    }
}
