package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class DepthPageTransformer extends ABaseTransformer {
    private static final float MIN_SCALE = 0.75F;

    public DepthPageTransformer() {
    }

    protected void onTransform(View view, float position) {
        if(position <= 0.0F) {
            view.setTranslationX(0.0F);
            view.setScaleX(1.0F);
            view.setScaleY(1.0F);
        } else if(position <= 1.0F) {
            float scaleFactor = 0.75F + 0.25F * (1.0F - Math.abs(position));
            view.setAlpha(1.0F - position);
            view.setPivotY(0.5F * (float)view.getHeight());
            view.setTranslationX((float)view.getWidth() * -position);
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }

    }

    protected boolean isPagingEnabled() {
        return true;
    }
}

