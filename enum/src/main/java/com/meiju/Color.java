package com.meiju;

/**
 * @author :覃玉锦
 * @create :2020-10-09 17:31:00
 */

interface mean{
    void meaning();
}

public enum  Color implements ReflectColor{
    RED(255,0,0),
    BLUE(0,0,255),
    BLACK(0,0,0),
    YELLOW(255,255,0),
    GREEN(0,255,0);

    private final int redValue;
    private final int greenValue;
    private final int blueValue;

    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public String toString() {
        return "Color{" +
                "redValue=" + redValue +
                ", greenValue=" + greenValue +
                ", blueValue=" + blueValue +
                '}';
    }

    @Override
    public void reflect() {
        System.out.println("反射"+this.name()+"的光");
    }
}
