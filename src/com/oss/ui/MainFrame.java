package com.oss.ui;

import com.oss.ui.test.PieChartExample;
import com.oss.dao.MemberDao;
import com.oss.model.Process;
import com.oss.model.TimeRecord;
import com.oss.ui.test.BarChartExample;

import java.awt.EventQueue;

import com.oss.util.UIUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public class MainFrame extends JFrame {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat moreSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	private JPanel contentPane;
	private PieChartExample pieChartExample;
	private BarChartExample barChartExample;
	private BarChartExample barChartExample2;
	private JTable table;
	private JButton excelButton;
	static String id;
	
	// Window 클래스형 ArrayList
	// 프로그램 명, 탭 명, 시작 시간이 들어감
	static ArrayList<Process> processes = new ArrayList<Process>();

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
	
	public MainFrame() {
		this(null);
	}

	/**
	 * Create the frame.
	 */
	public MainFrame(String id) {
		this.id = id;
		
		// 프로세스 정보 가져오기 시작
		GetWindowProcess gwp = new GetWindowProcess(id, processes);
		gwp.start();
		
		setTitle("Window Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton refreshButton = new JButton("새로 고침");
		refreshButton.setBackground(SystemColor.control);
		refreshButton.setFont(new Font("굴림", Font.PLAIN, 15));
		refreshButton.setBounds(12, 620, 136, 31);
		refreshButton.addActionListener(v -> {
        	dispose();
        	setVisible(false);
        	new MainFrame(id);
		});
		contentPane.add(refreshButton);
		
		JButton btnNewButton = new JButton("\uC0AC\uC6A9\uC885\uB8CC");
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBounds(836, 620, 136, 31);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				MemberDao dao = MemberDao.getInstance();
				
				if (result == JOptionPane.CLOSED_OPTION) { // 종료하지 않는다면
					
				} else if (result == JOptionPane.YES_OPTION) { // 종료할 것이라면
					dao.deleteProcessTable(id);
		        	
		        	dispose();
		        	setVisible(false);
		        	
		        	gwp.stop();
				} else {
					
				}

			}
		});
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
		tabPanel2.setBackground(Color.WHITE);
		tabbedPane.addTab("기록", null, tabPanel2, null);
		tabPanel2.setLayout(null);
		
		table = new JTable(getTableList());
		
		JScrollPane jspane = new JScrollPane(table);
		jspane.setBounds(12,55,931,496);
		tabPanel2.add(jspane);
		
		JLabel timeLabel_1 = new JLabel();
		timeLabel_1.setBounds(115, 10, 230, 28);
		tabPanel2.add(timeLabel_1);
		scheduledExecutorService.scheduleAtFixedRate(()->{
			timeLabel.setText(getNowString());
		},0, 1, TimeUnit.SECONDS);
		

		pieChartExample = new PieChartExample();
		pieChartExample.setData(getTimeRecord(getSimpleNowString()));
		tablPanel.add(pieChartExample.getChartPanel());
		
		barChartExample = new BarChartExample();
		barChartExample.setData(getTimeRecord(getSimpleNowString()));
		tablPanel.add(barChartExample.getBarChart());
		
		barChartExample2 = new BarChartExample();
		barChartExample2.setData(getTimeRecord(getSimpleNowString()));
		barChartExample2.getBarChart().setBounds(330,318,630,250);
		tablPanel.add(barChartExample2.getBarChart());

		
		JLabel userLabel = new JLabel(id);
		userLabel.setBounds(42, 14, 69, 24);
		tablPanel.add(userLabel);
		
		JButton userButton_1 = new JButton("");
		
		userButton_1.setBackground(Color.WHITE);
		userButton_1.addActionListener(v -> {
			setVisible(false);
			dispose();
			UserFrame userFrame = new UserFrame(id);
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
	
	private String getSimpleNowString() {
		Date now = new Date();
		return moreSimpleDateFormat.format(now);
	}
	/**
	 * 엑셀 버튼 보여주게 하기
	 * @param isShow 보여줄지 안 보여줄지
	 */
	private void showExcelButton(boolean isShow) {
		excelButton.setVisible(isShow);
	
	}
	
	private static Vector<String> getProcessRecord(){
		Vector<String> processInfo = new Vector<>();
		processInfo.add("프로그램");
		processInfo.add("제목");
		processInfo.add("시간");
		
		return processInfo;
	}
	
	private static DefaultTableModel getTableList() {
		// JTable 데이터 매핑하기 (데이터, 칼럼이름, 테이블모델)
		// 칼럼이름
		Vector<String> process = getProcessRecord();
		// 데이터
		MemberDao dao = MemberDao.getInstance();
		Vector<Process> processes = dao.findByAllProcess(id);
		// 테이블모델
		DefaultTableModel tableModel = new DefaultTableModel(process, 0);
		// for문 돌면서 한 행씩 데이터 집어 넣기
		for (int i = 0; i < processes.size(); i++) {
			Vector<Object> row = new Vector<>();
			row.addElement(processes.get(i).getProgramName());
			row.addElement(processes.get(i).getTabName());
			row.addElement(processes.get(i).getStartTime());
			tableModel.addRow(row); // table모델에 행 넣기
		}
		
		return tableModel;
	}
	
	private static Map<String, Long> getTimeRecord(String recordedDate) {
		Map<String, Long> USE_TIME_MAP = new HashMap<>();
		Map<String, Long> INSTANCE = null;
		
		MemberDao dao = MemberDao.getInstance();
		Vector<TimeRecord> timeRecord = dao.findByAllTimeRecord(id, recordedDate);

		for(int i = 0;i < timeRecord.size();i++) {
			USE_TIME_MAP.put(timeRecord.get(i).getProgramName(), timeRecord.get(i).getUsageTime());
		    INSTANCE = Collections.unmodifiableMap(USE_TIME_MAP);
		}

	    return INSTANCE;
	}

}


class GetWindowProcess extends Thread {
	ArrayList<Process> processes = new ArrayList<Process>();
	String id;
	Long start; // 시작 시간
	Long end;   // 종료 시간
	Long usageTime; // 사용 시간
	
	GetWindowProcess(String id, ArrayList<Process> processes){
		this.processes = processes;
		this.id = id;
	}
	
	public void run() {
		try {
			Process firstProcess = new Process();
			String processInfo;
			String[] ar;
			String tabName = "";
			String programName;
			char[] windowText = new char[512];
			
			MemberDao dao = MemberDao.getInstance();
			
	    	Date date_now = new Date(System.currentTimeMillis());
			SimpleDateFormat simple_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
			
			start = System.currentTimeMillis();
			
			// 최상단 프로그램 정보
			PointerType hwnd = User32.INSTANCE.GetForegroundWindow(); // then you can call it!
			User32.INSTANCE.GetWindowTextW(hwnd, windowText, 512);
			
			// split을 해 프로세스 정보에서 프로세스 이름만을 출력
		    processInfo = Native.toString(windowText);
		    ar = processInfo.split(" - ");
		    programName = ar[ar.length-1];
		    
		    // 탭 명
		    for(int i = 0;i < ar.length - 1;i++) {
		    	tabName += ar[i];
		  	  	if(i == ar.length - 2)
		  	  		break;
		    	tabName += " - ";
		    }
		    
		    if(programName.equals("")) {
		    	programName = "기타";
		    }
		    
		    // Window형 객체인 window에 값 넣어줌
		    firstProcess.setProgramName(programName);
		    firstProcess.setTabName(tabName);
		    firstProcess.setStartTime(simple_format.format(date_now));
		    processes.add(firstProcess);
		    // DB에 저장
		    dao.saveProcessRecord(id, firstProcess);
		    
		    Thread.sleep(1000);
			
		    while(true) {

		    	tabName = "";
		    	
		    	// 현재 시간 정보
		    	// 프로그램 사용 시작 시간을 위한 것
		    	date_now = new Date(System.currentTimeMillis());
				
				// 최상단 프로그램 정보
		    	hwnd = User32.INSTANCE.GetForegroundWindow();
				User32.INSTANCE.GetWindowTextW(hwnd, windowText, 512);
				
				// split을 해 프로세스 정보에서 프로세스 이름만을 출력
			    processInfo = Native.toString(windowText);
			    ar = processInfo.split(" - ");
			    programName = ar[ar.length-1];
			    
			    // 탭 명
			    for(int i = 0;i < ar.length - 1;i++) {
			    	tabName += ar[i];
			  	  	if(i == ar.length - 2)
			  	  		break;
			    	tabName += " - ";
			    }
			    
			    // 마지막 인덱스의 프로세스 이름 및 탭 이름이 현재 가져온 프로세스 정보와 일치할 경우
			    // (똑같은 창을 띄어놨을 경우)
			    // while 루프문 처음부터 다시 시작 (다시 가져옴)
			    if(programName.equals(processes.get(processes.size() - 1).getProgramName())
			    		&& tabName.equals(processes.get(processes.size() - 1).getTabName())) {
				    // 1초 주기로 반복
				    Thread.sleep(1000);
				    //j++;
				    
			    	continue;
			    }
			    
				// end - start는 ConnectionTime 요청을 받은 시점의 시간에서 처음 연결된 시간을 뺀 값

			    if(programName.equals("")) {
			    	programName = "기타";
			    }
				end = System.currentTimeMillis();

				usageTime = (end - start) / 1000;

				// 특정 날짜의 특정 프로그램이 사용시간 테이블에 존재하는지 조회
				// 만약 이미 존재한다면
				// 해당 값(시간)을 가져와 현재 시간에 더해줌 (사용시간 갱신)
				
				if(dao.checkTimeRecord(id, programName, date_format.format(date_now))) {
					System.out.println("사용시간은??? ==>" + dao.getTimeRecord(id, programName, date_format.format(date_now)));
					usageTime = usageTime + dao.getTimeRecord(id, programName, date_format.format(date_now));
					dao.updateTimeRecord(id, programName, date_format.format(date_now), usageTime);
				} else {
					// 새로운 프로그램에 대한 사용시간 저장
					dao.saveTimeRecord(id, programName, usageTime, date_format.format(date_now));
				}
				
				Process process = new Process();
		    	
			    // Window형 객체인 window에 값 넣어준 뒤, Wind ow형 ArrayList인 windows에 넣어줌
			    process.setProgramName(programName);
			    process.setTabName(tabName);
			    process.setStartTime(simple_format.format(date_now));
			    processes.add(process);
			    // DB에 저장
			    dao.saveProcessRecord(id, process);
			    
				start = System.currentTimeMillis();
			    
			    // 처음 인덱스 값 삭제
			    processes.remove(0);
			    // 1초 주기로 반복
			    Thread.sleep(1000);
		    }


		} catch(Exception e) {
			System.out.println("예외 발생");
		}
	}
	
	public interface User32 extends StdCallLibrary {
	    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
	    HWND GetForegroundWindow();  // add this
	    int GetWindowTextW(PointerType hWnd, char[] lpString, int nMaxCount);
	}

}