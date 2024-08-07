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

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JButton changePasswordButton;
	private JButton cancelButton;
	private JLabel lblNewLabel;
	private JPasswordField previousPasswordField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_2;
	static String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ChangePassword() {
		this(null);
	}
	

	/**
	 * Create the frame.
	 */
	public ChangePassword(String id) {
		this.id = id;
		setTitle("Window TIme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel changePassowordLable = new JLabel("\uC554\uD638 \uBCC0\uACBD");
		changePassowordLable.setFont(new Font("굴림", Font.BOLD, 20));
		changePassowordLable.setBounds(347, 10, 130, 35);
		contentPane.add(changePassowordLable);
		
		JLabel previousPassowordLabel = new JLabel("\uC774\uC804 \uC554\uD638");
		previousPassowordLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		previousPassowordLabel.setBounds(105, 104, 136, 35);
		contentPane.add(previousPassowordLabel);
		
		JLabel makePasswordLabel = new JLabel("\uC554\uD638 \uB9CC\uB4E4\uAE30");
		makePasswordLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		makePasswordLabel.setBounds(79, 165, 162, 35);
		contentPane.add(makePasswordLabel);
		
		JLabel confirmPasswordLabel = new JLabel("\uC554\uD638 \uD655\uC778");
		confirmPasswordLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		confirmPasswordLabel.setBounds(105, 232, 136, 35);
		contentPane.add(confirmPasswordLabel);
		
		changePasswordButton = new JButton("\uBCC0\uACBD");
		changePasswordButton.setBackground(SystemColor.control);
		changePasswordButton.setFont(new Font("굴림", Font.PLAIN, 30));
		changePasswordButton.setBounds(258, 443, 122, 54);
		contentPane.add(changePasswordButton);
		changePasswordButton.addActionListener(v->{

		});
		
		cancelButton = new JButton("\uCDE8\uC18C");
		cancelButton.setBackground(SystemColor.control);
		cancelButton.setFont(new Font("굴림", Font.PLAIN, 30));
		cancelButton.setBounds(472, 443, 122, 54);
		contentPane.add(cancelButton);
		
		lblNewLabel = new JLabel("8~16\uC790\uB9AC\uB85C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(542, 162, 192, 35);
		contentPane.add(lblNewLabel);
		
		previousPasswordField = new JPasswordField();
		previousPasswordField.setBounds(361, 104, 130, 38);
		contentPane.add(previousPasswordField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(361, 162, 130, 38);
		contentPane.add(passwordField);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(361, 232, 130, 38);
		contentPane.add(passwordField_2);

		changePasswordButton.addActionListener(v->{
			MemberDao dao = MemberDao.getInstance();
			
			String password = new String(passwordField.getPassword()); // 바꿀 비번 1
			String password2 = new String(passwordField_2.getPassword()); // 바꿀 비번 2
			String previousPassword = new String(previousPasswordField.getPassword()); // 이전 패스워드
			
			if(!password.equals(password2)) { // 비밀번호 두개 다를 때
				System.out.println("비밀번호가 일치하지 않습니다");
				JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다");
				passwordField.requestFocus(true);
			} else if(previousPassword.equals("")) { // 이전비밀번호가 빈문자
				System.out.println("비밀번호가 입력되지 않았습니다");
				JOptionPane.showMessageDialog(null,"이전비밀번호를 입력해주세요");
				previousPasswordField.requestFocus(true);
			} else if(password.equals("")) { //비밀번호가 빈문자
				System.out.println("비밀번호가 입력되지 않았습니다");
				JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요");
				passwordField.requestFocus(true);
			} else if (password.length() < 8 || password.length() > 16) { // 비밀번호 8~16 자리
				System.out.println("");
				JOptionPane.showMessageDialog(null, "비밀번호 8-16자리로 입력하세요");
				passwordField.requestFocus(true);
			} else {
				if(dao.updatePassword(id, previousPassword, password)) {
					JOptionPane.showMessageDialog(null, "비밀번호 변경 완료");
					dispose();
					new UserFrame(id);
				}
			}
		});
		
		cancelButton.addActionListener(v->{
			setVisible(false);
			dispose();
			new UserFrame(id);
		});
		setVisible(true);
		setResizable(false);
		
		UIUtil.centreWindow(this);
		setVisible(true);
	}
}