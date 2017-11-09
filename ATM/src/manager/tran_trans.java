package manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.ServerMaster;
import Server.UserDAO;

import java.sql.*;

public class tran_trans extends JFrame implements ActionListener {
	
	JLabel lbluser = new JLabel("이름: ");
	JLabel lblaccnum = new JLabel("계좌번호: ");
	JLabel lblpass = new JLabel("비밀번호: ");
	JLabel lblwid = new JLabel("송금할 금액: ");
	JLabel lblbal = new JLabel("잔액");

	JLabel lbluser2 = new JLabel("이름: ");
	JLabel lblaccnum2 = new JLabel("계좌번호: ");

	JButton btnback = new JButton("메인화면으로");
	JButton btnOK = new JButton("송금");
	
		
	JTextField txtuser = new JTextField(25);
	JTextField txtaccnum = new JTextField(25);
	JPasswordField txtpass = new JPasswordField(25);
	JTextField txtwid = new JTextField(25);
	JTextField txtbal = new JTextField(25);
	
	JTextField txtuser2 = new JTextField(25);
	JTextField txtaccnum2 = new JTextField(25);

	JLabel lblyour = new JLabel("Your Account");
	JLabel lblReciever = new JLabel("Reciever Account");

	public tran_trans() {
		
		super("Transfer Session");
		JPanel pane = new JPanel();
		pane.setLayout(null);
		
		pane.add(lbluser);
		pane.add(lblaccnum);
		pane.add(lblpass);
		pane.add(lblwid);
		pane.add(lblbal);
		
		pane.add(txtuser);
		pane.add(txtaccnum);
		pane.add(txtpass);
		pane.add(txtwid);
		pane.add(txtbal);
		
		pane.add(txtuser2);
		pane.add(txtaccnum2);
		pane.add(lbluser2);
		pane.add(lblaccnum2);
	
		pane.add(btnback);
		pane.add(btnOK);
	
		pane.add(lblyour);
		pane.add(lblReciever);
		
		lbluser.setBounds(10, 40, 80, 20);
		lblaccnum.setBounds(10, 60, 80, 20);
		lblpass.setBounds(10, 80, 80, 20);
		lblwid.setBounds(10, 100, 80, 20);
		lblbal.setBounds(10, 120, 80, 20);
	
		txtuser.setBounds(100, 40, 80, 20);
		txtaccnum.setBounds(100, 60, 80, 20);
		txtpass.setBounds(100, 80, 80, 20);
		txtwid.setBounds(100, 100, 80, 20);
		txtbal.setBounds(100, 120, 80, 20);
	
		lbluser2.setBounds(270, 40, 80, 20);
		lblaccnum2.setBounds(270, 60, 80, 20);
	
		txtuser2.setBounds(350, 40, 80, 20);
		txtaccnum2.setBounds(350, 60, 80, 20);
	
		lblyour.setBounds(100, 10, 140, 20);
		lblReciever.setBounds(350, 10, 140, 20);
	
		btnback.setBounds(175, 195, 140, 20);
		btnOK.setBounds(190, 160, 100, 20);
		txtbal.setEditable(false);

		btnback.addActionListener(this);
		btnOK.addActionListener(this);
	
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Transfer Transaction"));
	
		btnback.setToolTipText("Click First then Enter Your Money to Transfer");
	
		btnback.setToolTipText("Click To Back to Menu");
	
		btnback.setForeground(Color.yellow);
		btnOK.setForeground(Color.yellow);
		btnback.setBackground(Color.black);
		btnOK.setBackground(Color.black);
	
		lblyour.setForeground(Color.red);
		lblReciever.setForeground(Color.red);
	
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == btnback) {
	
			main panel=new main();
			
			panel.setSize(550,700);
			panel.setVisible(true);
			panel.setResizable(true);
			panel.setLocation(350,250);
			dispose();
	
		} else if (source == btnOK) {
			UserDAO udao = new UserDAO();
			UserBean ub = new UserBean();
			
				ub.setUname(txtuser.getText());
				ub.setUaccount(txtaccnum.getText());
				ub.setUpw(txtpass.getText());
				ub.setUamount(txtwid.getText());
				
				ub.setYname(txtuser2.getText());
				ub.setYaccount(txtaccnum2.getText());
				ServerMaster server = new ServerMaster();
				server.setUb(ub);
				boolean boolu = udao.confirm_login();
				boolean booly = udao.confirm_You_login();
				if(boolu==true&&booly==true){//로그인 성공시
					
					if(udao.Wdraw_trans()==true){//출금할 금액이 잔고를 넘지않을경우
						txtbal.setText(udao.getSum());//클라이언트에게 잔액주기
						udao.Wdraw_trans();//user는 출금
					    udao.You_dep();//You는 입금
					}
				    
				}else{
					JOptionPane.showMessageDialog(null, "입력하신 정보가 맞지 않거나 잔액이 충분하지 않습니다", "ATM",
							JOptionPane.WARNING_MESSAGE);
				}
			}
	} 
	
	public static void main(String[] args) {
		tran_trans log = new tran_trans();
	
		log.setLocation(400, 250);
		log.setSize(500, 280);
		log.setResizable(true);
		log.setVisible(true);
	}
}