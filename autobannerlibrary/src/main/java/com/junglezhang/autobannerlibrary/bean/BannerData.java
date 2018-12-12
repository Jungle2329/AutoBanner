package com.junglezhang.autobannerlibrary.bean;

import java.io.Serializable;

/**
 * Created by Jungle on 2018/12/11 0011.
 *
 * @desc 数据
 */

public class BannerData implements Serializable{
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
