package com.oss.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oss.util.UIUtil;

import com.oss.dao.MemberDao;
import com.oss.model.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private void showLoginFrame() {
		setVisible(false);
		dispose();
		new LoginFrame();   /*회원가입 성공시 호출화면 됨*/	
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setTitle("Window Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
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
		
		idCheckButton.setBackground(SystemColor.control);
		idCheckButton.setFont(new Font("굴림", Font.PLAIN, 30));
		idCheckButton.setBounds(542, 104, 163, 34);
		idCheckButton.addActionListener(v->{
			MemberDao dao = MemberDao.getInstance();
			
			if (idTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"ID를 입력해주세요.");
				idTextField.requestFocus(); // 포커스 이동
			} else {
				if (dao.checkID(idTextField.getText())) {
					JOptionPane.showMessageDialog(null, idTextField.getText()+"는 사용가능합니다.");  
				} else {
					JOptionPane.showMessageDialog(null, idTextField.getText()+"는 중복입니다.");
					idTextField.setText(""); // tfUserid의 text박스지우기
					idTextField.requestFocus(); // 포커스 이동
				}
			}
			
			System.out.println("id = "+idTextField.getText());
		});
		contentPane.add(idCheckButton);
		
		
		JButton registerButton = new JButton("\uAC00\uC785");
		
		registerButton.setBackground(SystemColor.control);
		registerButton.setFont(new Font("굴림", Font.PLAIN, 30));
		registerButton.setBounds(258, 443, 122, 54);
		registerButton.addActionListener(v->{
			String id = idTextField.getText();
			String name = nameTextField.getText();
			String password1 = new String(passwordField.getPassword());
			String password2 = new String(passwordField2.getPassword());
			
			Member member = new Member();
			member.setUserid(id);
			member.setPassword(password1);
			member.setName(name);
			MemberDao dao = MemberDao.getInstance();
			
			if(!password1.equals(password2)) { // 비밀번호 두개 다를 때
				System.out.println("비밀번호가 일치하지 않습니다");
				JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다");
				passwordField.requestFocus(true);
			}else if(id.equals("")){
				System.out.println("아이디가 입력되지 않았습니다");
				JOptionPane.showMessageDialog(null,"아이디를 입력해주세요");
				idTextField.requestFocus(true);
				// 아이디가 빈문자
			}else if(password1.equals("")) {
				System.out.println("비밀번호가 입력되지 않았습니다");
				JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요");
				passwordField.requestFocus(true);
				//비밀번호가 빈문자
			}else if(name.equals("")) {
				System.out.println("이름이 입력되지 않았습니다");
				JOptionPane.showMessageDialog(null,"이름을 입력해주세요");
				nameTextField.requestFocus(true);
				//이름이 빈문자
			}else if (password1.length() < 8 || password1.length() > 16) {
			     System.out.println("");
			     JOptionPane.showMessageDialog(null, "비밀번호 8-16자리로 입력하세요");
			     passwordField.requestFocus(true);
			     //비밀번호 8~16 자리
			} else {
				if (dao.saveUserInfo(member)) {
					// 회원가입과 동시에 해당 사용자에 대한 사용기록 테이블과 사용시간 테이블 생성
					dao.createProcessTable(id);
					dao.createTimeRecordTable(id);
					dao.saveSampleTimeRecord(id);
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					showLoginFrame();
				} else {
					JOptionPane.showMessageDialog(null, "기입된 내용이 잘못되었습니다.");
				}
			}
			
		});
		contentPane.add(registerButton);
		
		JButton cancelButton = new JButton("\uCDE8\uC18C");
		
		cancelButton.setBackground(SystemColor.control);
		cancelButton.setFont(new Font("굴림", Font.PLAIN, 30));
		cancelButton.setBounds(472, 443, 122, 54);
		cancelButton.addActionListener(v->{
			JFrame loginFrame = new LoginFrame();
			setVisible(false);
			dispose();
		});
		contentPane.add(cancelButton);
		
		JLabel lblNewLabel_5 = new JLabel("8~16\uC790\uB9AC\uB85C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(542, 162, 192, 35);
		contentPane.add(lblNewLabel_5);
		setResizable(false);
		
		UIUtil.centreWindow(this);
		setVisible(true);
	}
}
