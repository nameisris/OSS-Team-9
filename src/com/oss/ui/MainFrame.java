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
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	private JPanel contentPane;
	private PieChartExample pieChartExample;
	private BarChartExample barChartExample;
	private JTable table;
	private JButton excelButton;

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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(836, 620, 136, 31);
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBackground(SystemColor.control);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		excelButton = new JButton("\uC5D1\uC140\uB85C \uC800\uC7A5");
		excelButton.setBackground(SystemColor.control);
		excelButton.setFont(new Font("굴림", Font.PLAIN, 15));
		excelButton.setBounds(682, 620, 136, 31);
		contentPane.add(excelButton);
		tabbedPane.addChangeListener(l->{
			int index = tabbedPane.getSelectedIndex();
			
			if(index==0) {
				showExcelButton(false);
			}else if(index==1) {
				showExcelButton(true);
			}
		});
		tabbedPane.setBounds(12, 20, 960, 590);
		contentPane.add(tabbedPane);
		
		JPanel tablPanel = new JPanel();
		tablPanel.setBackground(Color.WHITE);
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
	
		String[] header = {"프로그램","제목","시간"};
		String[][] contents= {
				{"1","2","3"}
		};
		
		table = new JTable(contents, header);
		
		
		System.out.println(table.getColumnName(0));
		
		
		JScrollPane jspane = new JScrollPane(table);
		jspane.setBounds(12,55,931,496);
		tabPanel2.add(jspane);
		
		JLabel timeLabel_1 = new JLabel("New label");
		timeLabel_1.setBounds(115, 10, 230, 28);
		tabPanel2.add(timeLabel_1);
		scheduledExecutorService.scheduleAtFixedRate(()->{
			timeLabel.setText(getNowString());
		},0, 1, TimeUnit.SECONDS);
		
		pieChartExample = new PieChartExample();
		tablPanel.add(pieChartExample.getChartPanel());
		
		barChartExample = new BarChartExample();
		tablPanel.add(barChartExample.getBarChart());
		
		JLabel userLabel = new JLabel("New label");
		userLabel.setBounds(42, 14, 69, 24);
		tablPanel.add(userLabel);
		
		JButton userButton_1 = new JButton("");
		
		userButton_1.setBackground(Color.WHITE);
		userButton_1.addActionListener(v -> {
			setVisible(false);
			dispose();
			UserFrame userFrame = new UserFrame();
			//TODO user button
		});
		userButton_1.setBounds(5, 10, 30, 28);
		tablPanel.add(userButton_1);
		
		
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
	/**
	 * 엑셀 버튼 보여주게 하기
	 * @param isShow 보여줄지 안 보여줄지
	 */
	private void showExcelButton(boolean isShow) {
		excelButton.setVisible(isShow);
	
	}
}
