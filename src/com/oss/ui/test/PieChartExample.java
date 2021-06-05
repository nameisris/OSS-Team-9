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
 * ���� �׷��� ��� */
public class PieChartExample {
    private PieChart pieChart;
    XChartPanel<PieChart> xchartPanel;

    public PieChartExample() {
        pieChartInitialize(); // ���� ��Ʈ ����
        xchartPanel = new XChartPanel<>(pieChart); // �⺻ swing panel���� ��Ʈ�� �ø��� ���� Wrapper class�� XchartPanel ��� �ʿ�
        xchartPanel.setBounds(10, 350, 300, 200); //���� ��Ʈ�� ��Ÿ�� ��ġ �����ϴ� �κ�
    }
	public XChartPanel<PieChart> getChartPanel() {
	     return xchartPanel;
	}
    /**
     * ���� ��Ʈ�� ������ ���� �ֱ�
     */
    private void pieChartInitialize() {
        pieChart = new PieChartBuilder().width(300).height(300).title("���ð�").build(); // ������Ʈ ��ü ����
        Map<String, Long> data = DummyData.INSTANCE; // ������

        Map<String, Long> sortedMap = data.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); // ���ð� ���� ������ ������ ������ ���ʿ��� ��� ���� �������

        pieChart.getStyler().setSeriesColors(UIUtil.getColorArray(sortedMap.size())); // ��Ʈ�� ���� �ִ� �κ���
        pieChart.getStyler().setChartBackgroundColor(new Color(255,255,255));
        // ��Ʈ�� �� �ִ� �κ�
        sortedMap.forEach((k, v) -> {
            pieChart.addSeries(k, v);
        });

    }


    
    }

