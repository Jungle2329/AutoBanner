package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class FlipVerticalTransformer extends ABaseTransformer {
    public FlipVerticalTransformer() {
    }

    protected void onTransform(View view, float position) {
        float rotation = -180.0F * position;
        view.setAlpha(rotation <= 90.0F && rotation >= -90.0F?1.0F:0.0F);
        view.setPivotX((float)view.getWidth() * 0.5F);
        view.setPivotY((float)view.getHeight() * 0.5F);
        view.setRotationX(rotation);
    }

    protected void onPostTransform(View page, float position) {
        super.onPostTransform(page, position);
        if(position > -0.5F && position < 0.5F) {
            page.setVisibility(0);
        } else {
            page.setVisibility(4);
        }

    }
}
