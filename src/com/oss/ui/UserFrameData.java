package com.oss.ui;

import java.util.Map;
import java.util.Objects;

/**
 * ����� ���� ȭ���� �׷����� �� data object

 */
public class UserFrameData {
     private String dateString; // ��¥, Date �Ǵ� String ���, Date�� �� ��� User Frame���� formatting �ʿ�
    private Map<String, Long> programNameTimeMap; // �ش� ��¥�� ����� ���α׷� �̸�, �ð�(��) map��ü
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
