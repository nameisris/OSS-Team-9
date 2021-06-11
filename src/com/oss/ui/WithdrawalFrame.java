package com.oss.ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oss.util.UIUtil;

import com.oss.dao.MemberDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class WithdrawalFrame extends JFrame {

	private JPanel contentPane;
	private JButton withdrawalBtn;
	private JButton cancelButton;
	private JTextField passwordField;
	static String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawalFrame frame = new WithdrawalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public WithdrawalFrame() {
		this(null);
	}
	

	/**
	 * Create the frame.
	 */
	public WithdrawalFrame(String id) {
		this.id = id;
		
		setTitle("WindowTime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLable = new JLabel("회원 탈퇴");
		titleLable.setFont(new Font("굴림", Font.BOLD, 20));
		titleLable.setBounds(150, 30, 160, 30);
		contentPane.add(titleLable);
		
		JLabel PasswordLable = new JLabel("비밀번호");
		PasswordLable.setFont(new Font("굴림", Font.BOLD, 20));
		PasswordLable.setBounds(50, 120, 100, 20);
		contentPane.add(PasswordLable);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 115, 176, 35);
		contentPane.add(passwordField);
		
		cancelButton = new JButton("취소");
		cancelButton.setBackground(SystemColor.control);
		cancelButton.setFont(new Font("굴림", Font.PLAIN, 30));
		cancelButton.setBounds(50, 180, 120, 30);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(v->{
			setVisible(false);
			dispose();
			new UserFrame(id);
		});
		
		withdrawalBtn = new JButton("탈퇴");
		withdrawalBtn.setBackground(SystemColor.control);
		withdrawalBtn.setFont(new Font("굴림", Font.PLAIN, 30));
		withdrawalBtn.setBounds(200, 180, 120, 30);
		contentPane.add(withdrawalBtn);
		withdrawalBtn.addActionListener(v->{
			MemberDao dao = MemberDao.getInstance();
			
			if(passwordField.getText().equals("")) { // 비밀번호가 입력되지 않았다면
				passwordField.requestFocus(); // 포커스이동
			} else if(dao.checkPassword(id, passwordField.getText())) { // 비밀번호가 맞다면
				//탈퇴할지 묻기
				int result = JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.CLOSED_OPTION) { // 탈퇴하지 않는다면
					
				} else if (result == JOptionPane.YES_OPTION) { // 탈퇴할 것이라면
					//dao.deleteUser(id);
				} else {
					
				}
			} else { // 비밀번호가 틀렸다면
				JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
				passwordField.setText("");
				passwordField.requestFocus(); // 포커스이동
			}
		});
		
		UIUtil.centreWindow(this);
		setVisible(true);
	}
}
