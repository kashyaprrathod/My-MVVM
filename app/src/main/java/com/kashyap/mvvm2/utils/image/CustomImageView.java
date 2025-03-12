package com.kashyap.mvvm2.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.kashyap.mvvm2.R;
import com.kashyap.mvvm2.utils.image.model.EffectBase;
import com.kashyap.mvvm2.utils.image.model.ModelBorder;
import com.kashyap.mvvm2.utils.image.model.ModelDottedBorder;
import com.kashyap.mvvm2.utils.image.model.ModelRings;

public class CustomImageView extends View {

    int outerRadious = 100;

    EditingEffectHolder holder;
    EffectBase effectbase;

    Bitmap tempb;
    Canvas cnv;
    Paint maskingpaint;

    //border editing:-

    Paint borderPaint;
    LinearGradient gradient;

    //dotted editing:-
    Paint dottedPaint;

    Paint ringPaint;


    public CustomImageView(Context context) {
        super(context);

        setup(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    private void setup(Context c) {

        int w = (int) (c.getResources().getDisplayMetrics().widthPixels * 0.9);

        outerRadious = (w - (w / 15));
        outerRadious = outerRadious / 2;
        Log.d("TAG", "setup: " + outerRadious);

        maskingpaint = new Paint();

        PorterDuffXfermode xnode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        maskingpaint.setXfermode(xnode);
        maskingpaint.setColor(Color.TRANSPARENT);


        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);


        dottedPaint = new Paint();
        dottedPaint.setStyle(Paint.Style.STROKE);

        ringPaint = new Paint();
        ringPaint.setStyle(Paint.Style.STROKE);

    }

    public void setCurrentEffect(EditingEffectHolder holder) {
        this.holder = holder;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        createMaskedTransparentCircle();
        effectbase = holder.getModelAccordingtoCurrentEffect();

        if (effectbase instanceof ModelBorder) {
            setBorderEffect(canvas);
        } else if (effectbase instanceof ModelDottedBorder) {
            setDottedBorderEffect(canvas);

        } else if (effectbase instanceof ModelRings) {
            setRingEffect(canvas);
        }

    }

    private void setBorderEffect(Canvas canvas) {

        ModelBorder mborder = (ModelBorder) effectbase;

        gradient = new LinearGradient(0, 0, getWidth() / 2, getHeight() / 2,
                new int[]{mborder.getColor1(), mborder.getColor2()}, null, Shader.TileMode.REPEAT);

        borderPaint.setStrokeWidth(mborder.getBorderSize());
        borderPaint.setShader(gradient);
        borderPaint.setMaskFilter(new BlurMaskFilter(mborder.getShadowsize(), BlurMaskFilter.Blur.SOLID));

//        borderPaint.setShadowLayer(mborder.getShadowsize(), 5, 5, Color.BLACK);

//        Paint shaowPaint = new Paint();
//        shaowPaint.setStyle(Paint.Style.STROKE);
//        shaowPaint.setStrokeWidth(mborder.getBorderSize());
//        shaowPaint.setShader(gradient);
////        shaowPaint.setShadowLayer(mborder.getShadowsize(), 10, 10, Color.BLACK);
//        shaowPaint.setMaskFilter(new BlurMaskFilter(mborder.getShadowsize(), BlurMaskFilter.Blur.OUTER));
////        canvas.drawCircle(getWidth()/2,getHeight()/2,outerRadious+mborder.getShadowsize(),shaowPaint);

        cnv.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - mborder.getBorderSize() - 5, maskingpaint);
        canvas.drawBitmap(tempb, 0, 0, null);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious, borderPaint);

    }

    private void setDottedBorderEffect(Canvas canvas) {


        ModelDottedBorder mdotted = (ModelDottedBorder) effectbase;

        gradient = new LinearGradient(0, 0, getWidth() / 2, getHeight() / 2,
                new int[]{mdotted.getColor1(), mdotted.getColor2()}, null, Shader.TileMode.REPEAT);

        dottedPaint.setStrokeWidth(mdotted.getBorderSize());
        dottedPaint.setShader(gradient);
        dottedPaint.setMaskFilter(new BlurMaskFilter(mdotted.getShadowsize(), BlurMaskFilter.Blur.SOLID));
        dottedPaint.setPathEffect(new DashPathEffect(new float[]{65, mdotted.getDotSpacing()}, 20));

        cnv.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - mdotted.getBorderSize() - 5, maskingpaint);
        canvas.drawBitmap(tempb, 0, 0, null);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious, dottedPaint);

    }

    private void setRingEffect(Canvas canvas) {

        ModelRings mrings = (ModelRings) effectbase;

//        gradient = new LinearGradient(0, 0, getWidth() / 2, getHeight() / 2,
//                new int[]{mrings.getColor1(), mrings.getColor2()}, null, Shader.TileMode.REPEAT);


        ringPaint.setStrokeWidth(mrings.getBorderSize());
//        ringPaint.setShader(gradient);
        ringPaint.setMaskFilter(new BlurMaskFilter(mrings.getShadowsize(), BlurMaskFilter.Blur.SOLID));


        if (mrings.getRingCount() == ModelRings.RingCount.RING1) {
            cnv.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - mrings.getBorderSize() - 5, maskingpaint);
            canvas.drawBitmap(tempb, 0, 0, null);
            ringPaint.setColor(mrings.getColor1());
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious, ringPaint);
        } else if (mrings.getRingCount() == ModelRings.RingCount.RING2) {
            cnv.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - mrings.getBorderSize() - 30 - 5, maskingpaint);
            canvas.drawBitmap(tempb, 0, 0, null);
            ringPaint.setColor(mrings.getColor1());
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious, ringPaint);
            ringPaint.setColor(mrings.getColor2());
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - 30, ringPaint);
        } else if (mrings.getRingCount() == ModelRings.RingCount.RING3) {
            cnv.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - mrings.getBorderSize() - 60 - 5, maskingpaint);
            canvas.drawBitmap(tempb, 0, 0, null);
            ringPaint.setColor(mrings.getColor1());
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious, ringPaint);
            ringPaint.setColor(mrings.getColor2());
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - 30, ringPaint);
            ringPaint.setColor(mrings.getColor3());
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadious - 60, ringPaint);
        }

    }

    private void createMaskedTransparentCircle() {

        tempb = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        cnv = new Canvas(tempb);
        cnv.drawColor(getResources().getColor(R.color.errorColor));
    }
}