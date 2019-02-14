package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class ScaleInOutTransformer extends ABaseTransformer {
    public ScaleInOutTransformer() {
    }

    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0.0F?0.0F:(float)view.getWidth());
        view.setPivotY((float)view.getHeight() / 2.0F);
        float scale = position < 0.0F?1.0F + position:1.0F - position;
        view.setScaleX(scale);
        view.setScaleY(scale);
    }
}