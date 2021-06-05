package com.oss.ui.test;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.*;

import com.oss.util.UIUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ���� ��� �׷��� */
public class BarChartExample {
    private CategoryChart categoryChart;
    private XChartPanel<CategoryChart> xchartPanel;

    public BarChartExample() {
        categoryChartInitialize(); // ī�װ� ��Ʈ ����
        xchartPanel = new XChartPanel<>(categoryChart); // �⺻ swing panel���� ��Ʈ�� �ø��� ���� Wrapper class�� XchartPanel ��� �ʿ�
        xchartPanel.setBounds(10, 50, 950, 280); //��Ʈ�� ��Ÿ�� ��ġ �����ϴ� �κ�
    }
    
    public XChartPanel<CategoryChart> getBarChart() {
    	return xchartPanel;
    }

    /**
     * ī�װ� ��Ʈ �ʱ�ȭ
     */
    private void categoryChartInitialize() {
        categoryChart = new CategoryChartBuilder().width(400).height(400).title("���ð�").build();

        Map<String, Long> data = DummyData.INSTANCE; // ������

        Map<String, Long> sortedMap = data.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); // ���ð� ���� ������ ������ ������ ���ʿ��� ��� ���� �������


        categoryChart.getStyler().setSeriesColors(UIUtil.getColorArray(1));
        java.util.List<String> graphList = new ArrayList<>(sortedMap.keySet()); // �׷��� X��
        java.util.List<Long> useList = new ArrayList<>(sortedMap.values()); // �׷��� Y��
        categoryChart.addSeries("���ð�", graphList, useList); // �׷��� �߰�
    }
}
