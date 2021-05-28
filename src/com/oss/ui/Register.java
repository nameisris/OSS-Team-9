package com.oss.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oss.util.UIUtil;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		Font font30 = new Font("굴림", Font.PLAIN, 30);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(347, 10, 130, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(105, 104, 96, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(105, 162, 122, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblNewLabel_3.setFont(font30);
		lblNewLabel_3.setBounds(105, 232, 201, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC774\uB984");
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(105, 319, 96, 35);
		contentPane.add(lblNewLabel_4);
		
		idTextField = new JTextField();
		idTextField.setBounds(361, 104, 130, 38);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(361, 319, 130, 38);
		contentPane.add(nameTextField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(361, 162, 130, 38);
		contentPane.add(passwordField);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(361, 232, 130, 38);
		contentPane.add(passwordField2);
		
		JButton idCheckButton = new JButton("\uC911\uBCF5\uD655\uC778");
		idCheckButton.setFont(new Font("굴림", Font.PLAIN, 30));
		idCheckButton.setBounds(542, 104, 163, 34);
		idCheckButton.addActionListener(v->{
			System.out.println("id = "+idTextField.getText());
		});
		contentPane.add(idCheckButton);
		
		
		JButton registerButton = new JButton("\uAC00\uC785");
		registerButton.setFont(new Font("굴림", Font.PLAIN, 30));
		registerButton.setBounds(258, 443, 122, 54);
		registerButton.addActionListener(v->{
			String id = idTextField.getText();
			String name = nameTextField.getText();
			String password1 = new String(passwordField.getPassword());
			String password2 = new String(passwordField2.getPassword());
			
			if(!password1.equals(password2)) { // 비밀번호 두개 다를 때
				System.out.println("비밀번호 두개가 다르다");
			}else {
				// 아이디 중복 체크
				// 계속 진행
			}
		});
		contentPane.add(registerButton);
		
		JButton cancelButton = new JButton("\uCDE8\uC18C");
		cancelButton.setFont(new Font("굴림", Font.PLAIN, 30));
		cancelButton.setBounds(472, 443, 114, 54);
		cancelButton.addActionListener(v->{
			JFrame loginFrame = new Login();
			setVisible(false);
			dispose();
		});
		contentPane.add(cancelButton);
		
		UIUtil.centreWindow(this);
		setVisible(true);
	}

}
