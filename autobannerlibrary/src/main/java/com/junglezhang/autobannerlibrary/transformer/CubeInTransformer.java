package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class CubeInTransformer extends ABaseTransformer {

    public CubeInTransformer() {
    }

    protected void onTransform(View view, float position) {
        view.setPivotX(position > 0.0F ? 0.0F : (float) view.getWidth());
        view.setPivotY(0.0F);
        view.setRotationY(-90.0F * position);
    }

    public boolean isPagingEnabled() {
        return true;
    }
}
