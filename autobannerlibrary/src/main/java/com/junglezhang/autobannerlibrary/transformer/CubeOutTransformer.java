package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class CubeOutTransformer extends ABaseTransformer {
    public CubeOutTransformer() {
    }

    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0.0F?(float)view.getWidth():0.0F);
        view.setPivotY((float)view.getHeight() * 0.5F);
        view.setRotationY(90.0F * position);
    }

    public boolean isPagingEnabled() {
        return true;
    }
}