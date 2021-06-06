package com.oss.ui.test;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.*;

import com.oss.util.UIUtil;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 막대 모양 그래프 */
public class BarChartExample {
    private CategoryChart categoryChart;
    private XChartPanel<CategoryChart> xchartPanel;
    private Map<String, Long> data;

    public BarChartExample() {
      
    }
    
    public XChartPanel<CategoryChart> getBarChart() {
    	return xchartPanel;
    }
    
    public void setData(Map<String, Long> data) {
        this.data = data;
        categoryChartInitialize();
        xchartPanel = new XChartPanel<>(categoryChart); // �⺻ swing panel���� ��Ʈ�� �ø��� ���� Wrapper class�� XchartPanel ��� �ʿ�
        xchartPanel.setBounds(10, 50, 950, 280); //��Ʈ�� ��Ÿ�� ��ġ �����ϴ� �κ�

    }

    /**
     * 카테고리 차트 초기화
     */
    private void categoryChartInitialize() {
        categoryChart = new CategoryChartBuilder().width(400).height(400).title("사용시간").build();


        Map<String, Long> sortedMap = data.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); // 사용시간 많은 순으로 소팅함 소팅이 불필요할 경우 빼도 상관없음


        categoryChart.getStyler().setSeriesColors(UIUtil.getColorArray(1));
        java.util.List<String> graphList = new ArrayList<>(sortedMap.keySet()); // 그래프 X축
        java.util.List<Long> useList = new ArrayList<>(sortedMap.values());
        categoryChart.getStyler().setYAxisTicksVisible(false);// 그래프 Y축
        categoryChart.addSeries("사용시간", graphList, useList); // 그래프 추가
        categoryChart.getStyler().setChartBackgroundColor(new Color(255,255,255));
        categoryChart.getStyler().setHasAnnotations(true);//그래프안에 숫자 넣는거
        
    }
}
