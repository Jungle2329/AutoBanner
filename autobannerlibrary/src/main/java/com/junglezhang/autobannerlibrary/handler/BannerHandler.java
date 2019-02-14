package com.junglezhang.autobannerlibrary.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Jungle on 2018/7/2 0002.
 *
 * @desc 轮播图统一处理
 */

public class BannerHandler extends Handler {

    /**
     * 请求更新显示的View。
     */
    public static final int MSG_UPDATE_IMAGE = 21;
    /**
     * 请求暂停轮播。
     */
    public static final int MSG_KEEP_SILENT = 22;
    /**
     * 请求恢复轮播。
     */
    public static final int MSG_BREAK_SILENT = 23;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    public static final int MSG_PAGE_CHANGED = 24;

    //轮播间隔时间
    public static final long MSG_DELAY = 4000;

    //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
    private WeakReference<Activity> weakReference;
    private int currentItem = 0;
    private boolean isAutoPlay;
    private OnNextPage mOnNextPage;

    public BannerHandler(Activity reference, OnNextPage mOnNextPage,boolean isAutoPlay) {
        weakReference = new WeakReference<>(reference);
        this.mOnNextPage = mOnNextPage;
        this.isAutoPlay = isAutoPlay;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        Activity activity = weakReference.get();
        if (activity == null) {
            //Activity已经回收，无需再处理UI了
            return;
        }
        //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
        if (hasMessages(MSG_UPDATE_IMAGE)) {
            removeMessages(MSG_UPDATE_IMAGE);
        }
        switch (msg.what) {
            case MSG_UPDATE_IMAGE:
                if(isAutoPlay) {
                    currentItem++;
                    mOnNextPage.onNextPage(currentItem);
                }
                //准备下次播放
                sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_KEEP_SILENT:
                //只要不发送消息就暂停了
                break;
            case MSG_BREAK_SILENT:
                sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_PAGE_CHANGED:
                //记录当前的页号，避免播放的时候页面显示不正确。
                currentItem = msg.arg1;
                break;
            default:
                break;
        }
    }

    public interface OnNextPage {
        void onNextPage(int item);
    }
}
