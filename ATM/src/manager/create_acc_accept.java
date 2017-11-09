package manager;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import Server.ServerMaster;
import Server.UserDAO;

public class create_acc_accept extends JFrame implements ActionListener{
	
	
	
	
	
	JLabel lb1 = new JLabel("날짜");
	JLabel lb2 = new JLabel("고객명");
	JLabel lb3 = new JLabel("계좌번호");
	
	
	JButton btnOK = new JButton("승인");
	JButton btnback = new JButton("메인화면으로");
	
	

	public create_acc_accept(){
		
		UserBean ub1 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub1 = server.getUb();
		
		JLabel lbudate = new JLabel(ub1.getUdate());
		JLabel lbuname = new JLabel(ub1.getUname());
		JLabel lbuaccount = new JLabel(ub1.getUaccount());
		//db에 있는 account와 중복확인하기
		JPanel pane = new JPanel();
		
		pane.setLayout(null);
		
		pane.add(lb1);
		pane.add(lb2);
		pane.add(lb3);
		pane.add(btnOK);
		pane.add(btnback);
		pane.add(lbudate);
		pane.add(lbuname);
		pane.add(lbuaccount);
		pane.add(lbudate);
		pane.add(lbuname);
		pane.add(lbuaccount);
		
		lb1.setBounds(50, 10, 300, 100);
		lb2.setBounds(200,10, 300, 100);
		lb3.setBounds(350,10, 300, 100);
		lbudate.setBounds(50, 50, 300, 100);
		lbuname.setBounds(200,50,300,100);
		lbuaccount.setBounds(350, 50, 300, 100);
		btnOK.setBounds(500, 100, 70, 30);
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
		}
		else if(source == btnOK){

			UserDAO udao = new UserDAO();
			udao.createAccount();
			accept_ok log=new accept_ok();
			
			log.setLocation(400,250);
			log.setSize(400,300);
			log.setTitle("ATM_관리자_계좌생성승인성공");
			log.setResizable(false);
			log.setVisible(true);
		
			
		}
	}
	
	public static void main(String[] args){
		
		create_acc_accept panel = new create_acc_accept();
		
		panel.setLocation(350,250);
		panel.setSize(600,400);
		panel.setTitle("ATM_관리자_계좌생성승인");
		panel.setResizable(false);
		panel.setVisible(true);
		
	}
	
	
	
}
