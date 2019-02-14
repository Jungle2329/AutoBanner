package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class StackTransformer extends ABaseTransformer {
    public StackTransformer() {
    }

    protected void onTransform(View view, float position) {
        view.setTranslationX(position < 0.0F?0.0F:(float)(-view.getWidth()) * position);
    }
}
