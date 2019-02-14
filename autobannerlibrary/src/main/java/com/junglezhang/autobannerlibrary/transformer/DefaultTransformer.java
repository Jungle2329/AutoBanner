package com.junglezhang.autobannerlibrary.transformer;

import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class DefaultTransformer extends ABaseTransformer {
    public DefaultTransformer() {
    }

    protected void onTransform(View view, float position) {
    }

    public boolean isPagingEnabled() {
        return true;
    }
}

