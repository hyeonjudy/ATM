package manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.ServerMaster;
import Server.UserDAO;

public class create_acc extends JFrame implements ActionListener{
	
	JLabel lb1 = new JLabel("고객명");
	JLabel lb2 = new JLabel("계좌번호");
	JLabel lb3 = new JLabel("비밀번호");

	JTextField username = new JTextField();
	
	UserDAO udao = new UserDAO();
	JTextField useraccount = new JTextField(udao.getAccount());
	
	JTextField userpw = new JTextField();
	
	UserBean ub = new UserBean();
	
	JButton btnOK = new JButton("등록");
	JButton btnback = new JButton("메인화면으로");
	
	public create_acc(){
		JPanel pane = new JPanel();
		pane.setLayout(null);
	
		pane.add(lb1);pane.add(username);
		pane.add(lb2);pane.add(useraccount);
		pane.add(lb3);pane.add(userpw);
		pane.add(btnOK);
		pane.add(btnback);
		
		lb1.setBounds(50, 10, 300, 100);
		lb2.setBounds(50,60, 300, 100);
		lb3.setBounds(50,110, 300, 100);
		username.setBounds(250,50,100,20);
		useraccount.setBounds(250,100,100,20);
		userpw.setBounds(250,150,100,20);
		btnOK.setBounds(230, 270, 70, 30);
		btnback.setBounds(200, 300, 140, 30);
		
		btnOK.setForeground(Color.red);
		btnOK.setBackground(Color.white);
		btnback.setForeground(Color.black);
		btnback.setBackground(Color.yellow);
		btnOK.addActionListener(this);
		btnback.addActionListener(this);
		
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnback){
			main panel=new main();
			
			panel.setSize(550,700);
			panel.setVisible(true);
			panel.setResizable(true);
			panel.setLocation(350,250);
			dispose();
		}else if(source==btnOK){
			
			ub.setUname(username.getText());
			ub.setUaccount(useraccount.getText());
			ub.setUpw(userpw.getText());
			ServerMaster server = new ServerMaster();
			server.setUb(ub);
			udao.createAccount();
			
			create_ok log=new create_ok();
			
			log.setLocation(400,250);
			log.setSize(400,300);
			log.setTitle("ATM_관리자_계좌생성성공");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
			
			
		}
	}
	
	public static void main(String[] args){
		
		create_acc log=new create_acc();
		
		log.setLocation(350,250);
		log.setSize(600,400);
		log.setTitle("ATM_관리자_계좌생성");
		log.setResizable(false);
		log.setVisible(true);
		
	}
}
