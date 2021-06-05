package com.oss.util;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * UI ���� ��ƿ��Ƽ
 */
public class UIUtil {
    private static Random random = new Random();
    /**
     * ���� ���ʿ� Ŭ������
     */
    private UIUtil() {
    }

    /**
     * frame�� ȭ���� ���߾� location���� �������ش�.
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
     * �׷����� �� �����͵鿡 ���� ������ ������ �̾Ƴ���.
     *
     * @param count �̾ƾ� �� ����(������) ����
     * @return ���� ���� �迭
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
