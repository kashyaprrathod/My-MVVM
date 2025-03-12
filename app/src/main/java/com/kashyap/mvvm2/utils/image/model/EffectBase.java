package com.kashyap.mvvm2.utils.image.model;

import android.graphics.Color;

public abstract class EffectBase {

    int bordersize = 20;
    int color1 = Color.BLACK,color2 = Color.BLACK,color3 = Color.BLACK;
    int shadowsize =1;

    public abstract void setBordersize(int bordersize);

    public abstract void setColor1(int color);

    public abstract void setColor2(int color);

    public abstract void setColor3(int color);

    public abstract void setShadowsize(int shadowsize);

    public abstract int getBorderSize();

    public abstract int getColor1();

    public abstract int getColor2();

    public abstract int getColor3();

    public abstract int getShadowsize();


}
