package manager;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.ServerMaster;
import Server.UserDAO;

import java.sql.*;

public class wdr_trans extends JFrame implements ActionListener {
	
	JLabel lbluser = new JLabel("이름");
	JLabel lblaccnum = new JLabel("계좌번호");
	JLabel lblpass = new JLabel("비밀번호: ");
	JLabel lblwdraw = new JLabel("출금금액: ");
	JLabel lblbal = new JLabel("잔액: ");
	JButton btnback = new JButton("메인화면으로");
	JButton btnOK = new JButton("출금");
	
	JTextField txtuser = new JTextField(25);
	JTextField txtaccnum = new JTextField(25);
	JPasswordField txtpass = new JPasswordField(25);
	JTextField txtwdraw = new JTextField(25);
	JTextField txtbal = new JTextField(25);
	

	public wdr_trans() {
		
		super("Withdraw Transaction");
	
		JPanel pane = new JPanel();
		pane.setLayout(null);
		
		pane.add(lbluser);
		pane.add(lblpass);
		pane.add(lblwdraw);
		pane.add(lblbal);
		pane.add(lblaccnum);
		pane.add(txtuser);
		pane.add(txtpass);
		pane.add(txtwdraw);
		pane.add(txtbal);
		pane.add(txtaccnum);	
		pane.add(btnback);
		pane.add(btnOK);
		
		lbluser.setBounds(10, 20, 100, 20);
		lblaccnum.setBounds(10, 40, 100, 20);
		lblpass.setBounds(10, 60, 100, 20);
		lblwdraw.setBounds(10, 80, 100, 20);
		lblbal.setBounds(10, 100, 100, 20);//
		txtuser.setBounds(100, 20, 100, 20);
		txtaccnum.setBounds(100, 40, 100, 20);
		txtpass.setBounds(100, 60, 100, 20);
		txtwdraw.setBounds(100, 80, 100, 20);
		txtbal.setBounds(100, 100, 100, 20);
		btnback.setBounds(80, 160, 140, 20);
		btnOK.setBounds(100, 130, 100, 20);
		
		btnback.addActionListener(this);
		btnOK.addActionListener(this);
	
		txtbal.setEditable(false);
		btnback.setToolTipText("Click To Back to Menu");
		btnback.setForeground(Color.yellow);
		btnOK.setForeground(Color.yellow);
		btnback.setBackground(Color.black);
		btnOK.setBackground(Color.black);
	
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Withdraw Transaction"));
	
		JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
	
		lbl.setBounds(0, 0, 250, 200);
		pane.add(lbl);
	
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
				ub.setUamount(txtwdraw.getText());
				ServerMaster server = new ServerMaster();
				server.setUb(ub);
				if(udao.Wdraw_trans()==true){
					txtbal.setText(udao.getSum());
					txtbal.setEditable(true);
					JOptionPane.showMessageDialog(null, "출금이 정상적으로 처리되었습니다.");
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "입력하신 정보가 맞지 않거나 잔액이 충분하지 않습니다", "ATM",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
	}
	

public static void main(String[] args) {
	wdr_trans log = new wdr_trans();

	log.setLocation(400, 250);
	log.setSize(250, 240);
	log.setResizable(false);
	log.setVisible(true);

}
}