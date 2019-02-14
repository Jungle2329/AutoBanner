package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class AccordionTransformer extends ABaseTransformer {
    public AccordionTransformer() {
    }

    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0.0F?0.0F:(float)view.getWidth());
        view.setScaleX(position < 0.0F?1.0F + position:1.0F - position);
    }
}
