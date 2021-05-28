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

public class Login extends JFrame {

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
					Login frame = new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("\u25CBWindow Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		lblNewLabel.setBounds(139, 186, 101, 63);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(112, 276, 128, 63);
		contentPane.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		idField.setBounds(324, 199, 150, 47);
		contentPane.add(idField);
		idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		passwordField.setBounds(324, 289, 150, 47);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		loginButton.setBounds(512, 199, 128, 50);
		
		loginButton.addActionListener((v)->{
			String id = idField.getText();
			String password = new String(passwordField.getPassword());
			System.out.println("·Î±×ÀÎ ¹öÆ°");
			System.out.println("id = "+id);
			System.out.println("password = " + password);
		});
		
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		registerButton.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		registerButton.setBounds(512, 289, 179, 50);
		
		registerButton.addActionListener( v -> {
			// È¸¿ø°¡ÀÔ Ã³¸®
			JFrame register = new Register();
			setVisible(false);
			dispose();
		});
		contentPane.add(registerButton);
		
		JLabel lblNewLabel_2 = new JLabel("Window Time");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(287, 45, 263, 47);
		contentPane.add(lblNewLabel_2);
		

		UIUtil.centreWindow(this);
		setVisible(true);
	}
}
