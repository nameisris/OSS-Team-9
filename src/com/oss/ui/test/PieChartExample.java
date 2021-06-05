package com.oss.ui.test;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import com.oss.util.UIUtil;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 원형 그래프 모양 */
public class PieChartExample {
    private PieChart pieChart;
    XChartPanel<PieChart> xchartPanel;

    public PieChartExample() {
        pieChartInitialize(); // 파이 차트 설정
        xchartPanel = new XChartPanel<>(pieChart); // 기본 swing panel에는 차트를 올릴수 없어 Wrapper class인 XchartPanel 사용 필요
        xchartPanel.setBounds(10, 350, 300, 200); //파이 차트가 나타날 위치 설정하는 부분
    }
	public XChartPanel<PieChart> getChartPanel() {
	     return xchartPanel;
	}
    /**
     * 파이 차트에 데이터 집어 넣기
     */
    private void pieChartInitialize() {
        pieChart = new PieChartBuilder().width(300).height(300).title("사용시간").build(); // 파이차트 객체 생성
        Map<String, Long> data = DummyData.INSTANCE; // 데이터

        Map<String, Long> sortedMap = data.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); // 사용시간 많은 순으로 소팅함 소팅이 불필요할 경우 빼도 상관없음

        pieChart.getStyler().setSeriesColors(UIUtil.getColorArray(sortedMap.size())); // 차트에 색깔 넣는 부분임
        pieChart.getStyler().setChartBackgroundColor(new Color(255,255,255));
        // 차트에 값 넣는 부분
        sortedMap.forEach((k, v) -> {
            pieChart.addSeries(k, v);
        });

    }


    
    }

