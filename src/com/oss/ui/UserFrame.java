package com.oss.ui;

import com.oss.ui.test.BarChartExample;
import com.oss.util.UIUtil;

import com.oss.dao.MemberDao;
import com.oss.model.Member;
import com.oss.model.TimeRecord;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.IntStream;

public class UserFrame extends JFrame {

    private JPanel contentPane;
    private List<UserFrameData> userFrameDataList;
    private JPanel buttonPanel;
    private JPanel graphPanel;
	static String id;

    public void setUserFrameDataList(List<UserFrameData> userFrameDataList) {
        this.userFrameDataList = userFrameDataList;
        GridBagConstraints constraints = new GridBagConstraints();
        userFrameDataList.forEach(userFrameData -> {
            JButton button = new JButton(userFrameData.getDateString());
            button.addActionListener(action -> {
                // 여기서부터 날짜 버튼 눌렀을 때 처리 시작
                refreshGraphPanel();
                BarChartExample barChartExample = new BarChartExample();
                barChartExample.setData(userFrameData.getProgramNameTimeMap());
                barChartExample.getBarChart().setBounds(0, 0, 0, 0);
                graphPanel.add(barChartExample.getBarChart());
                graphPanel.repaint();
                graphPanel.revalidate();
            });
            constraints.gridx++;
            buttonPanel.add(button, constraints);
        });
        repaint();
        revalidate();
    }

   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserFrame frame = new UserFrame();
                frame.setVisible(true);
                // 임시 데이터
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public UserFrame() {
    	this(null);
    }

    /**
     * Create the frame.
     */
    public UserFrame(String id) {
    	this.id = id;
    	
        setTitle("Window Time");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel userInformationLabel = new JLabel("\uC0AC\uC6A9\uC790 \uC815\uBCF4");
        userInformationLabel.setFont(new Font("굴림", Font.BOLD, 25));
        userInformationLabel.setBounds(12, 14, 139, 35);
        contentPane.add(userInformationLabel);

        JLabel idLabel = new JLabel("\uC544\uC774\uB514:");
        idLabel.setFont(new Font("굴림", Font.BOLD, 20));
        idLabel.setBounds(12, 72, 70, 56);
        contentPane.add(idLabel);

        JLabel nameLabel = new JLabel("\uC774\uB984:");
        nameLabel.setFont(new Font("굴림", Font.BOLD, 20));
        nameLabel.setBounds(33, 111, 54, 56);
        contentPane.add(nameLabel);
        
		MemberDao dao = MemberDao.getInstance();
		Member member = new Member();
		member = dao.getUserInfo(member, id);

        JLabel userIdLabel = new JLabel(member.getUserid());
        userIdLabel.setBounds(94, 95, 81, 15);
        contentPane.add(userIdLabel);

        JLabel userNameLabel = new JLabel(member.getName());
        userNameLabel.setBounds(94, 134, 81, 15);
        contentPane.add(userNameLabel);

        JButton changePasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
        changePasswordButton.addActionListener(e->{
        	setVisible(false);
        	dispose();
        	new ChangePassword(id);
        });
        changePasswordButton.setBackground(Color.WHITE);
        changePasswordButton.setFont(new Font("굴림", Font.PLAIN, 15));
        changePasswordButton.setBounds(12, 489, 139, 23);
        contentPane.add(changePasswordButton);

        JButton deleteUserButton = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
        deleteUserButton.addActionListener(e->{
        	setVisible(false);
        	dispose();
        	new WithdrawalFrame(id);
        });
        deleteUserButton.setBackground(Color.WHITE);
        deleteUserButton.setFont(new Font("굴림", Font.PLAIN, 15));
        deleteUserButton.setBounds(12, 552, 139, 23);
        contentPane.add(deleteUserButton);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBounds(500, 600, 450, 50);
        contentPane.add(buttonPanel);

        graphPanel = new JPanel();
        graphPanel.setBounds(500, 100, 450, 450);
        contentPane.add(graphPanel);
        
        JButton cancelButton = new JButton("\uCDE8\uC18C");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.addActionListener(v->{
        	dispose();
        	setVisible(false);
        	// 보낼 화면 생성
        	new MainFrame(id);
        	
        });
        cancelButton.setFont(new Font("굴림", Font.PLAIN, 15));
        cancelButton.setBounds(12, 628, 139, 23);
        contentPane.add(cancelButton);

    	Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
    	
        List<UserFrameData> userFrameDataList = new ArrayList<>();
        Map<String,Long> timeRecordDataMap = getTimeRecord(date_format.format(date_now));
        IntStream.range(1,6).forEach(i->{
            UserFrameData userFrameData = new UserFrameData();
            userFrameData.setDateString("2021-06-0"+i);
            userFrameData.setProgramNameTimeMap(timeRecordDataMap);
            userFrameDataList.add(userFrameData);
        });
        setUserFrameDataList(userFrameDataList);
        UIUtil.centreWindow(this);
        setVisible(true);
    }

    /**
     * 그래프 패널 초기화 작업
     */
    private void refreshGraphPanel() {
        graphPanel.removeAll();
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