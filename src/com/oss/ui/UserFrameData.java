package com.oss.ui;

import java.util.Map;
import java.util.Objects;

/**
 * 사용자 정보 화면의 그래프에 들어갈 data object
 */
public class UserFrameData {
     private String dateString; // 날짜, Date 또는 String 사용, Date로 할 경우 User Frame에서 formatting 필요
    private Map<String, Long> programNameTimeMap; // 해당 날짜에 사용한 프로그램 이름, 시간(초) map객체
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getDateString() {
        return dateString;
    }

    public void setProgramNameTimeMap(Map<String, Long> programNameTimeMap) {
        this.programNameTimeMap = programNameTimeMap;
    }

    public Map<String, Long> getProgramNameTimeMap() {
        return programNameTimeMap;
    }
}
