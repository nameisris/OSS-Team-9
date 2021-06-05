package com.oss.ui;

import com.oss.ui.test.PieChartExample;
import com.oss.ui.test.BarChartExample;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.oss.util.UIUtil;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	private JPanel contentPane;
	private PieChartExample pieChartExample;
	private BarChartExample barChartExample;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Window Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\uC0AC\uC6A9\uC885\uB8CC");
		btnNewButton.setBounds(836, 620, 136, 31);
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 20, 960, 590);
		contentPane.add(tabbedPane);
		
		JPanel tablPanel = new JPanel();
		tabbedPane.addTab("사용 시간", null, tablPanel, null);
		tablPanel.setLayout(null);
		
		JLabel timeLabel = new JLabel("");
		timeLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		timeLabel.setBounds(115, 10, 230, 28);
		timeLabel.setText(getNowString());
		tablPanel.add(timeLabel);
		JPanel tabPanel2 = new JPanel();
		tabbedPane.addTab("기록", null, tabPanel2, null);
		tabPanel2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 41, 318, 510);
		tabPanel2.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("\uD504\uB85C\uADF8\uB7A8");
		textField.setBounds(0, 0, 318, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(318, 41, 305, 510);
		tabPanel2.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(623, 41, 332, 510);
		tabPanel2.add(panel_2);
		scheduledExecutorService.scheduleAtFixedRate(()->{
			timeLabel.setText(getNowString());
		},0, 1, TimeUnit.SECONDS);
		
		pieChartExample = new PieChartExample();
		tablPanel.add(pieChartExample.getChartPanel());
		
		barChartExample = new BarChartExample();
		tablPanel.add(barChartExample.getBarChart());
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(34, 14, 69, 24);
		tablPanel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(0, 10, 32, 28);
		tablPanel.add(btnNewButton_1);
		
		UIUtil.centreWindow(this);
		setVisible(true);
	}
	
	/**
	 * 스케줄링 서비스 중단하는 메소드
	 */
	private void shudown() {
		scheduledExecutorService.shutdownNow();
	}
	
	private String getNowString() {
		Date now = new Date();
		return dateFormat.format(now);
	}
}
