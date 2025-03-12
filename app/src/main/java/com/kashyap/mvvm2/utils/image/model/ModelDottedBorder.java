package com.kashyap.mvvm2.utils.image.model;

public class ModelDottedBorder extends EffectBase {

    int dotSpacing = 20;

    @Override
    public void setBordersize(int bordersize) {
        this.bordersize = bordersize;
    }

    @Override
    public void setColor1(int color) {
        this.color1 = color;
    }

    @Override
    public void setColor2(int color) {
        this.color2 = color;
    }

    @Override
    public void setColor3(int color) {

    }

    @Override
    public void setShadowsize(int shadowsize) {
        this.shadowsize = shadowsize;
    }

    @Override
    public int getBorderSize() {
        return this.bordersize;
    }

    @Override
    public int getColor1() {
        return this.color1;
    }

    @Override
    public int getColor2() {
        return this.color2;
    }

    @Override
    public int getColor3() {
        return 0;
    }

    @Override
    public int getShadowsize() {
        return this.shadowsize;
    }


    public int getDotSpacing() {
        return dotSpacing;
    }

    public void setDotSpacing(int dotSpacing) {
        this.dotSpacing = dotSpacing;
    }
}
