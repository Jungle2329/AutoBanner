package com.junglezhang.autobannerlibrary.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.junglezhang.autobannerlibrary.bean.BannerData;
import com.junglezhang.autobannerlibrary.handler.BannerHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jungle on 2018/12/10 0010.
 *
 * @desc TODO
 */

public class AutoBanner extends ViewPager {

    private Context context;
    private BannerHandler mImageHandler;
    private OnBannerChangeListener bannerListener;
    private List<?> bannerList = new ArrayList<>();

    public AutoBanner(Context context) {
        super(context);
        this.context = context;
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public <T extends BannerData> void setAdapterData(List<T> bannerList) {
        this.bannerList = bannerList;
        InnerPagerAdapter<T> mAdapter = new InnerPagerAdapter<>(bannerList);
        setAdapter(mAdapter);
        initViewPager();
        setCurrentItem(bannerList.size() * 3000);
    }

    public List<?> getAdapterData() {
        return bannerList;
    }

    public void setOnBannerChangeListener(OnBannerChangeListener bannerListener) {
        this.bannerListener = bannerListener;
    }

    /**
     * 开始轮播效果
     */
    private void initViewPager() {
        mImageHandler = new BannerHandler((Activity) context, new BannerHandler.OnNextPage() {
            @Override
            public void onNextPage(int item) {
                setCurrentItem(item);
            }
        });
        mImageHandler.sendEmptyMessageDelayed(BannerHandler.MSG_UPDATE_IMAGE, BannerHandler.MSG_DELAY);
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //配合Adapter的currentItem字段进行设置。
            @Override
            public void onPageSelected(int poisiton) {
                if (bannerListener != null) {
                    bannerListener.onBannerSelected(poisiton);
                }
                mImageHandler.sendMessage(Message.obtain(mImageHandler, BannerHandler.MSG_PAGE_CHANGED, poisiton, 0));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (bannerListener != null) {
                    bannerListener.onBannerScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            //覆写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int state) {
                if (bannerListener != null) {
                    bannerListener.onBannerScrollStateChanged(state);
                }
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mImageHandler.sendEmptyMessage(BannerHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        mImageHandler.sendEmptyMessage(BannerHandler.MSG_BREAK_SILENT);
                        break;
                    default:
                        break;
                }
            }
        });
        mImageHandler.sendEmptyMessage(BannerHandler.MSG_BREAK_SILENT);
    }


    class InnerPagerAdapter<K extends BannerData> extends BaseInfinitePagerAdapter {

        private List<K> bannerList = new ArrayList<>();

        public InnerPagerAdapter(@NotNull List<K> bannerList) {
            super(bannerList);
            this.bannerList = bannerList;
        }

        @NotNull
        @Override
        public Object instantiateItemForce(@NotNull ViewGroup container, int position) {
            SimpleDraweeView sdv = new SimpleDraweeView(context);
            sdv.setImageURI(Uri.parse(bannerList.get(position % bannerList.size()).getImage()));
            sdv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            container.addView(sdv);
            return sdv;
        }
    }

    public interface OnBannerChangeListener {

        void onBannerScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onBannerSelected(int position);

        void onBannerScrollStateChanged(int state);
    }
}
