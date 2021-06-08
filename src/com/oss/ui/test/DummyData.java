package com.oss.ui.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 더미데이터(임시 테스트용 데이터)
 
 */
public class DummyData {
    /**
     * String : 최상위 프로그램 명
     * Long : 사용 시간(초) 라고 가정한다.
     */
    private static final Map<String, Long> USE_TIME_MAP = new HashMap<>();

    /**
     * 실제 공유할 Map
     * UnModifiedMap을 공유해서 다른 데서는 값 수정 불가능하도록함
     */
    public static final Map<String, Long> INSTANCE;

    /**
     * Map의 값을 정적 초기화 한다.
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
     * 객체 생성 불필요
     */
    private DummyData() {
    }
}