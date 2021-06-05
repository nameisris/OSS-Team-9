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
import java.awt.SystemColor;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Window Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 360);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		lblNewLabel.setBounds(0, 125, 74, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(0, 215, 128, 63);
		contentPane.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		idField.setBounds(104, 126, 150, 47);
		contentPane.add(idField);
		idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		passwordField.setBounds(104, 224, 150, 47);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.addActionListener(new ActionListener() {
		});
		loginButton.setBackground(SystemColor.control);
		loginButton.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		loginButton.setBounds(266, 125, 134, 46);
		
		loginButton.addActionListener((v)->{
			String id = idField.getText();
			String password = new String(passwordField.getPassword());
			System.out.println("·Î±×ÀÎ ¹öÆ°");
			System.out.println("id = "+id);
			System.out.println("password = " + password);
		});
		
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		registerButton.addActionListener(new ActionListener() {
		});
		registerButton.setBackground(SystemColor.control);
		registerButton.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		registerButton.setBounds(266, 223, 134, 47);
		
		registerButton.addActionListener( v -> {
			// È¸¿ø°¡ÀÔ Ã³¸®
			JFrame register = new RegisterFrame();
			setVisible(false);
			dispose();
		});
		contentPane.add(registerButton);
		
		JLabel lblNewLabel_2 = new JLabel("Window Time");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(71, 28, 263, 47);
		contentPane.add(lblNewLabel_2);
		

		UIUtil.centreWindow(this);
		setVisible(true);
	}
}
