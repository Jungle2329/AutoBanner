package com.junglezhang.autobannerlibrary.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.junglezhang.autobannerlibrary.R;

import java.util.List;

/**
 * Created by Jungle on 2018/12/11 0011.
 *
 * @desc banner指示器
 */

public class AutoBannerIndicator extends View {

    private Paint mPaint;
    private Paint mPaintDisable;
    private int width;
    private int height;
    private AutoBanner mBannerView;
    //当前位置
    private int position;
    //当前的偏移量[0,1)
    private float positionOffset;
    //点之间的间隔
    private final int POINT_GAP = 40;
    private int pointGap;
    //点的半径
    private final int POINT_R = 10;
    private int pointR;

    private int rectWidth;
    private int rectHeight;
    private RectF currentRect = new RectF();
    private List<?> dataList;
    private int enableColor;
    private int disableColor;


    public AutoBannerIndicator(Context context) {
        this(context, null);
    }

    public AutoBannerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoBannerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handlerTypeArray(context, attrs);
        init();
    }

    private void handlerTypeArray(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoBannerIndicator);
        pointR = mTypedArray.getDimensionPixelSize(R.styleable.AutoBannerIndicator_ab_radio, POINT_R);
        pointGap = mTypedArray.getDimensionPixelSize(R.styleable.AutoBannerIndicator_ab_point_gap, POINT_GAP);
        enableColor = mTypedArray.getColor(R.styleable.AutoBannerIndicator_ab_enable_color, 0xffffffff);
        disableColor = mTypedArray.getColor(R.styleable.AutoBannerIndicator_ab_disable_color, 0xff000000);
        mTypedArray.recycle();
    }


    private void init() {
        //主色
        mPaint = new Paint();
        mPaint.setColor(enableColor);
        mPaint.setAntiAlias(true);
        //辅助色
        mPaintDisable = new Paint();
        mPaintDisable.setColor(disableColor);
        mPaintDisable.setAntiAlias(true);
        //矩形的宽高
        rectWidth = pointGap;
        rectHeight = pointR * 2;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //indicator的位置
        width = pointGap / 2;
        height = getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (dataList == null || dataList.isEmpty())
            return;
        for (int i = 0; i < dataList.size(); i++) {
            canvas.drawCircle(width + i * pointGap, height, pointR, mPaintDisable);
        }
        int currentPoint = position % dataList.size();
        float offset = pointGap * positionOffset;
        if (currentPoint + 1 == dataList.size()) {
            currentRect.set(width + pointGap * currentPoint - rectWidth / 2, height - rectHeight / 2
                    , width + pointGap * currentPoint + rectWidth / 2, height + rectHeight / 2);
        } else {
            currentRect.set(width + pointGap * currentPoint - rectWidth / 2 + offset, height - rectHeight / 2
                    , width + pointGap * currentPoint + rectWidth / 2 + offset, height + rectHeight / 2);
        }
        canvas.drawRoundRect(currentRect, pointR * 2, pointR * 2, mPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
//        switch (MeasureSpec.getMode(widthMeasureSpec)) {
//            case MeasureSpec.EXACTLY://match_content或者是直接指定大小
//
//                break;
//            case MeasureSpec.AT_MOST://wrap_content
//
//                break;
//            case MeasureSpec.UNSPECIFIED://父容器不对子view做任何限制
//
//                break;
//        }
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {//宽高都是wrap
            w = pointGap * dataList.size();
            h = pointR * 2;
        } else if (widthMode == MeasureSpec.AT_MOST) {//宽是wrap
            w = pointGap * dataList.size();
        } else {//高是wrap
            h = pointR * 2;
        }
        //给两个字段设置值，完成最终测量
        setMeasuredDimension(w, h);
    }

    /**
     * 与banner绑定
     *
     * @param mBanner
     */
    public void bindBanner(AutoBanner mBanner) {
        mBannerView = mBanner;
        dataList = mBannerView.getBannerData();
        mBanner.setOnBannerChangeListener(new AutoBanner.OnBannerChangeListener() {
            @Override
            public void onBannerScrolled(int position, float positionOffset, int positionOffsetPixels) {
                AutoBannerIndicator.this.position = position;
                AutoBannerIndicator.this.positionOffset = positionOffset;
                requestLayout();
                invalidate();
            }

            @Override
            public void onBannerSelected(int position) {
            }

            @Override
            public void onBannerScrollStateChanged(int state) {
            }
        });

    }

}
