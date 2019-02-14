package com.junglezhang.autobannerlibrary.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public abstract class ABaseTransformer implements ViewPager.PageTransformer {
    public ABaseTransformer() {
    }

    protected abstract void onTransform(View var1, float var2);

    public void transformPage(View page, float position) {
        this.onPreTransform(page, position);
        this.onTransform(page, position);
        this.onPostTransform(page, position);
    }

    protected boolean hideOffscreenPages() {
        return true;
    }

    protected boolean isPagingEnabled() {
        return false;
    }

    protected void onPreTransform(View page, float position) {
        float width = (float)page.getWidth();
        page.setRotationX(0.0F);
        page.setRotationY(0.0F);
        page.setRotation(0.0F);
        page.setScaleX(1.0F);
        page.setScaleY(1.0F);
        page.setPivotX(0.0F);
        page.setPivotY(0.0F);
        page.setTranslationY(0.0F);
        page.setTranslationX(this.isPagingEnabled()?0.0F:-width * position);
        if(this.hideOffscreenPages()) {
            page.setAlpha(position > -1.0F && position < 1.0F?1.0F:0.0F);
        } else {
            page.setAlpha(1.0F);
        }

    }

    protected void onPostTransform(View page, float position) {
    }

    protected static final float min(float val, float min) {
        return val < min?min:val;
    }
}
