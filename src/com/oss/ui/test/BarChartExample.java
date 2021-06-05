package com.oss.ui.test;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.*;

import com.oss.util.UIUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 막대 모양 그래프 */
public class BarChartExample {
    private CategoryChart categoryChart;
    private XChartPanel<CategoryChart> xchartPanel;

    public BarChartExample() {
        categoryChartInitialize(); // 카테고리 차트 설정
        xchartPanel = new XChartPanel<>(categoryChart); // 기본 swing panel에는 차트를 올릴수 없어 Wrapper class인 XchartPanel 사용 필요
        xchartPanel.setBounds(10, 50, 950, 280); //차트가 나타날 위치 설정하는 부분
    }
    
    public XChartPanel<CategoryChart> getBarChart() {
    	return xchartPanel;
    }

    /**
     * 카테고리 차트 초기화
     */
    private void categoryChartInitialize() {
        categoryChart = new CategoryChartBuilder().width(400).height(400).title("사용시간").build();

        Map<String, Long> data = DummyData.INSTANCE; // 데이터

        Map<String, Long> sortedMap = data.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); // 사용시간 많은 순으로 소팅함 소팅이 불필요할 경우 빼도 상관없음


        categoryChart.getStyler().setSeriesColors(UIUtil.getColorArray(1));
        java.util.List<String> graphList = new ArrayList<>(sortedMap.keySet()); // 그래프 X축
        java.util.List<Long> useList = new ArrayList<>(sortedMap.values()); // 그래프 Y축
        categoryChart.addSeries("사용시간", graphList, useList); // 그래프 추가
    }
}
