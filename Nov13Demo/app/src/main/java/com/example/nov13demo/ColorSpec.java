package com.example.nov13demo;

public class ColorSpec {

    private String colorDesc;   //e.g. "BLACK"
    private int ColorVal;      //e.g. Color.Black

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public int getColorVal() {
        return ColorVal;
    }

    public void setColorVal(int colorVal) {
        ColorVal = colorVal;
    }

    public ColorSpec(String colorDesc, int colorVal) {
        this.colorDesc = colorDesc;
        ColorVal = colorVal;
    }

}
