package project;

public class Stick {

    static final int  MIN_LENGTH = 0;
    static final int  MAX_LENGTH = 10000;

    int length;
    boolean isHorizontal;
    // While a touch is detected, the stick grows in length with a constant px-speed
    void grow() {}

    // After the finger is pulled up, the stick stops growing and is rotated by 90° towards the next pillar
    void rotate() {}

}
