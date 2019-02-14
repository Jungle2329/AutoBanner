package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class ZoomInTransformer extends ABaseTransformer {
    public ZoomInTransformer() {
    }

    protected void onTransform(View view, float position) {
        float scale = position < 0.0F?position + 1.0F:Math.abs(1.0F - position);
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setPivotX((float)view.getWidth() * 0.5F);
        view.setPivotY((float)view.getHeight() * 0.5F);
        view.setAlpha(position >= -1.0F && position <= 1.0F?1.0F - (scale - 1.0F):0.0F);
    }
}