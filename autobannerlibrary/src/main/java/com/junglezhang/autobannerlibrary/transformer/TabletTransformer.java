package com.junglezhang.autobannerlibrary.transformer;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

/**
 * Created by Jungle on 2019/2/14 0014.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */

public class TabletTransformer extends ABaseTransformer {
    private static final Matrix OFFSET_MATRIX = new Matrix();
    private static final Camera OFFSET_CAMERA = new Camera();
    private static final float[] OFFSET_TEMP_FLOAT = new float[2];

    public TabletTransformer() {
    }

    protected void onTransform(View view, float position) {
        float rotation = (position < 0.0F?30.0F:-30.0F) * Math.abs(position);
        view.setTranslationX(getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
        view.setPivotX((float)view.getWidth() * 0.5F);
        view.setPivotY(0.0F);
        view.setRotationY(rotation);
    }

    protected static final float getOffsetXForRotation(float degrees, int width, int height) {
        OFFSET_MATRIX.reset();
        OFFSET_CAMERA.save();
        OFFSET_CAMERA.rotateY(Math.abs(degrees));
        OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
        OFFSET_CAMERA.restore();
        OFFSET_MATRIX.preTranslate((float)(-width) * 0.5F, (float)(-height) * 0.5F);
        OFFSET_MATRIX.postTranslate((float)width * 0.5F, (float)height * 0.5F);
        OFFSET_TEMP_FLOAT[0] = (float)width;
        OFFSET_TEMP_FLOAT[1] = (float)height;
        OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
        return ((float)width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0F?1.0F:-1.0F);
    }
}
