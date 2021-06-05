package com.oss.util;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * UI 공통 유틸리티
 */
public class UIUtil {
    private static Random random = new Random();
    /**
     * 생성 불필요 클래스임
     */
    private UIUtil() {
    }

    /**
     * frame을 화면의 정중앙 location으로 설정해준다.
     *
     * @param frame frame
     */
    public static void centreWindow(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * 그래프에 들어갈 데이터들에 대해 랜덤한 색상을 뽑아낸다.
     *
     * @param count 뽑아야 할 색상(데이터) 개수
     * @return 랜덤 색깔 배열
     */
    public static Color[] getColorArray(int count) {
        Color[] result = new Color[count];
        for (int i = 0; i < count; i++) {
            int red = random.nextInt(255);
            int blue = random.nextInt(255);
            int green = random.nextInt(255);
            result[i] = new Color(red, green, blue);
        }
        return result;
    }
}
