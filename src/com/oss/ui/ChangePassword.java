package com.oss.ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oss.util.UIUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JLabel nameLabel;
	private JButton changePasswordButton;
	private JButton cancelButton;
	private JLabel lblNewLabel;
	private JPasswordField previousPasswordField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_2;
	private JTextField nameField;

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

	/**
	 * Create the frame.
	 */
	public ChangePassword() {
		setTitle("Window TIme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel changePassowordLable = new JLabel("\uC554\uD638 \uBCC0\uACBD");
		changePassowordLable.setFont(new Font("����", Font.BOLD, 20));
		changePassowordLable.setBounds(347, 10, 130, 35);
		contentPane.add(changePassowordLable);
		
		JLabel previousPassowordLabel = new JLabel("\uC774\uC804 \uC554\uD638");
		previousPassowordLabel.setFont(new Font("����", Font.PLAIN, 30));
		previousPassowordLabel.setBounds(105, 104, 136, 35);
		contentPane.add(previousPassowordLabel);
		
		JLabel makePasswordLabel = new JLabel("\uC554\uD638 \uB9CC\uB4E4\uAE30");
		makePasswordLabel.setFont(new Font("����", Font.PLAIN, 30));
		makePasswordLabel.setBounds(79, 165, 162, 35);
		contentPane.add(makePasswordLabel);
		
		JLabel confirmPasswordLabel = new JLabel("\uC554\uD638 \uD655\uC778");
		confirmPasswordLabel.setFont(new Font("����", Font.PLAIN, 30));
		confirmPasswordLabel.setBounds(105, 232, 136, 35);
		contentPane.add(confirmPasswordLabel);
		
		nameLabel = new JLabel("\uC774\uB984");
		nameLabel.setFont(new Font("����", Font.PLAIN, 30));
		nameLabel.setBounds(172, 309, 61, 35);
		contentPane.add(nameLabel);
		
		changePasswordButton = new JButton("\uBCC0\uACBD");
		changePasswordButton.setBackground(SystemColor.control);
		changePasswordButton.setFont(new Font("����", Font.PLAIN, 30));
		changePasswordButton.setBounds(258, 443, 122, 54);
		contentPane.add(changePasswordButton);
		
		cancelButton = new JButton("\uCDE8\uC18C");
		cancelButton.setBackground(SystemColor.control);
		cancelButton.setFont(new Font("����", Font.PLAIN, 30));
		cancelButton.setBounds(472, 443, 122, 54);
		contentPane.add(cancelButton);
		
		lblNewLabel = new JLabel("8~16\uC790\uB9AC\uB85C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
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
		
		nameField = new JTextField();
		nameField.setBounds(361, 319, 130, 38);
		contentPane.add(nameField);
		nameField.setColumns(10);
		changePasswordButton.addActionListener(v->{
			String password = new String(passwordField.getPassword()); // �ٲ� ��� 1
			String password2 = new String(passwordField_2.getPassword()); // �ٲ� ��� 2
			String name = nameField.getText(); // �̸�
			String previousPassword = new String(previousPasswordField.getPassword()); // ���� �н�����
			if(!password.equals(password2)) { // ��й�ȣ �ΰ� �ٸ� ��
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				JOptionPane.showMessageDialog(null,"��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				previousPasswordField.requestFocus(true);
		}else if(previousPassword.equals("")) {
				System.out.println("��й�ȣ�� �Էµ��� �ʾҽ��ϴ�");
				JOptionPane.showMessageDialog(null,"������й�ȣ�� �Է����ּ���");
				previousPasswordField.requestFocus(true);
				//������й�ȣ�� ����
		}else if(password.equals("")) {
			System.out.println("��й�ȣ�� �Էµ��� �ʾҽ��ϴ�");
			JOptionPane.showMessageDialog(null,"��й�ȣ�� �Է����ּ���");
			previousPasswordField.requestFocus(true);
			//��й�ȣ�� ����
		}else if(name.equals("")) {
			System.out.println("�̸��� �Էµ��� �ʾҽ��ϴ�");
			JOptionPane.showMessageDialog(null,"�̸��� �Է����ּ���");
			nameField.requestFocus(true);
			//�̸��� ����
		}else if (password.length() < 8 || password.length() > 16) {
		     System.out.println("");
		     JOptionPane.showMessageDialog(null, "��й�ȣ 8-16�ڸ��� �Է��ϼ���");
		     previousPasswordField.requestFocus(true);
		     //��й�ȣ 8~16 �ڸ�
		}
		});
		
		cancelButton.addActionListener(v->{
			setVisible(false);
			dispose();
			UserFrame userFrame = new UserFrame();
			
			
		});
		setVisible(true);
		setResizable(false);
		
		UIUtil.centreWindow(this);
		setVisible(true);
	}
}
