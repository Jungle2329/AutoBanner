package com.junglezhang.autobannerlibrary.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import com.junglezhang.autobannerlibrary.R;
import com.junglezhang.autobannerlibrary.handler.BannerHandler;
import com.junglezhang.autobannerlibrary.utils.ViewPagerScroller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jungle on 2018/12/10 0010.
 *
 * @desc Banner容器
 */

public class AutoBanner extends ViewPager {

    private final static String TAG = "AutoBanner";
    private Context context;
    private int mScrollDuration = 800;
    private boolean isAuto = true;
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
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoBanner);
        mScrollDuration = mTypedArray.getInt(R.styleable.AutoBanner_ab_scroll_duration, mScrollDuration);
        isAuto = mTypedArray.getBoolean(R.styleable.AutoBanner_ab_auto_play, isAuto);
        mTypedArray.recycle();
    }

    private void init() {
        ViewPagerScroller mScroller = new ViewPagerScroller(context);
        mScroller.setScrollDuration(mScrollDuration);
        mScroller.initViewPagerScroll(this);
    }

    public <K extends BaseInfinitePagerAdapter> void startWithAdapter(K mAdapter) {
        this.bannerList = mAdapter.getDataList();
        init();
        setAdapter(mAdapter);
        initViewPager();
        setCurrentItem(bannerList.size() * 3000);
        /** 重新请求轮播滚动 */
        mImageHandler.sendEmptyMessage(BannerHandler.MSG_BREAK_SILENT);
    }

    public List<?> getBannerData() {
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
        }, isAuto);
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
        mImageHandler.sendEmptyMessageDelayed(BannerHandler.MSG_UPDATE_IMAGE, BannerHandler.MSG_DELAY);
    }

    /**
     * 设置是否自动播放
     * 支持xml设置
     *
     * @param isAuto
     * @return
     */
    public AutoBanner setAutoPlay(boolean isAuto) {
        this.isAuto = isAuto;
        return this;
    }

    /**
     * 设置滚动速度
     * 支持xml设置
     * @param mScrollDuration
     * @return
     */
    public AutoBanner setScrollDuration(int mScrollDuration) {
        this.mScrollDuration = mScrollDuration;
        return this;
    }

    /**
     * 设置viewpager动画效果
     * 不支持xml设置
     * @param transformer
     * @return
     */
    public AutoBanner setBannerAnimation(Class<? extends PageTransformer> transformer) {
        try {
            setPageTransformer(true, transformer.newInstance());
        } catch (Exception var3) {
            Log.e(TAG, "Please set the PageTransformer class");
        }
        return this;
    }


    public interface OnBannerChangeListener {

        void onBannerScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onBannerSelected(int position);

        void onBannerScrollStateChanged(int state);
    }
}
