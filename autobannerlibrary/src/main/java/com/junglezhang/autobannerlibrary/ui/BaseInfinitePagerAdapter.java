package com.junglezhang.autobannerlibrary.ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jungle on 2018/12/11 0011.
 *
 * @desc 无限循环的轮播的基类
 */

public abstract class BaseInfinitePagerAdapter<T> extends PagerAdapter {

    private List<T> bannerList = new ArrayList<>();

    public BaseInfinitePagerAdapter(List<T> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public int getCount() {
        // 设置成最大，使用户看不到边界
        if (bannerList.size() == 1) {
            return bannerList.size();
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return instantiateItemForce(container, position, bannerList);
    }


    protected abstract Object instantiateItemForce(ViewGroup container, int position, List<T> list);

    public List<T> getDataList() {
        return bannerList;
    }
}
