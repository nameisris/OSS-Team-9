package com.oss.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oss.util.UIUtil;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class UserFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
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
	public UserFrame() {
		setTitle("Window Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userInformationLabel = new JLabel("\uC0AC\uC6A9\uC790 \uC815\uBCF4");
		userInformationLabel.setFont(new Font("±¼¸²", Font.BOLD, 25));
		userInformationLabel.setBounds(12, 14, 139, 35);
		contentPane.add(userInformationLabel);
		
		JLabel idLabel = new JLabel("\uC544\uC774\uB514:");
		idLabel.setFont(new Font("±¼¸²", Font.BOLD, 20));
		idLabel.setBounds(12, 72, 70, 56);
		contentPane.add(idLabel);
		
		JLabel nameLabel = new JLabel("\uC774\uB984:");
		nameLabel.setFont(new Font("±¼¸²", Font.BOLD, 20));
		nameLabel.setBounds(33, 111, 54, 56);
		contentPane.add(nameLabel);
		
		JLabel userIdLabel = new JLabel("New label");
		userIdLabel.setBounds(94, 95, 81, 15);
		contentPane.add(userIdLabel);
		
		JLabel userNameLabel = new JLabel("New label");
		userNameLabel.setBounds(94, 134, 81, 15);
		contentPane.add(userNameLabel);
		
		JButton changePasswordButton = new JButton("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
		changePasswordButton.setBackground(Color.WHITE);
		changePasswordButton.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		changePasswordButton.setBounds(12, 489, 139, 23);
		contentPane.add(changePasswordButton);
		
		JButton deleteUserButton = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		deleteUserButton.setBackground(Color.WHITE);
		deleteUserButton.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		deleteUserButton.setBounds(12, 552, 139, 23);
		contentPane.add(deleteUserButton);
		
		UIUtil.centreWindow(this);
		setVisible(true);
	}

}
