package com.vgaw.test;

/**
 * Created by Administrator on 2015/8/21.
 */
public class Test {
    public static void main(String[] args){
        EnumTest firstDay = new EnumTest(EnumTest.Day.MONDAY);
        firstDay.tellItLikeItIs();
        EnumTest thirdDay = new EnumTest(EnumTest.Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
        EnumTest fifthDay = new EnumTest(EnumTest.Day.FRIDAY);
        fifthDay.tellItLikeItIs();
        EnumTest sixthDay = new EnumTest(EnumTest.Day.SATURDAY);
        sixthDay.tellItLikeItIs();
        EnumTest seventhDay = new EnumTest(EnumTest.Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }
}
