package com.oss.ui.test;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ���̵�����(�ӽ� �׽�Ʈ�� ������)
 
 */
public class DummyData {
    /**
     * String : �ֻ��� ���α׷� ��
     * Long : ��� �ð�(��) ��� �����Ѵ�.
     */
    private static final Map<String, Long> USE_TIME_MAP = new HashMap<>();

    /**
     * ���� ������ Map
     * UnModifiedMap�� �����ؼ� �ٸ� ������ �� ���� �Ұ����ϵ�����
     */
    public static final Map<String, Long> INSTANCE;

    /**
     * Map�� ���� ���� �ʱ�ȭ �Ѵ�.
     */
    static {
        USE_TIME_MAP.put("IntelliJ IDEA", 3600L);
        USE_TIME_MAP.put("Chrome", 7200L);
        USE_TIME_MAP.put("Android Studio", 4800L);
        USE_TIME_MAP.put("KakaoTalk", 5000L);
        USE_TIME_MAP.put("Toolbox", 1000L);
        INSTANCE = Collections.unmodifiableMap(USE_TIME_MAP);
    }

    /**
     * ��ü ���� ���ʿ�
     */
    private DummyData() {
    }
}