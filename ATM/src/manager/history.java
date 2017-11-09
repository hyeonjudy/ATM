package manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.UserDAO;

public class history extends JFrame implements ActionListener{
	JLabel lb1 = new JLabel("번호");
	JLabel lb2 = new JLabel("고객명");
	JLabel lb3 = new JLabel("계좌번호");
	JLabel lb4 = new JLabel("종류(입금/출금)");
	JLabel lb5 = new JLabel("거래금액");
	JLabel lb6 = new JLabel("잔액");
	JLabel lb7 = new JLabel("날짜");
	
	
	JButton btnback = new JButton("메인화면으로");
	
	
	public history(){
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.add(lb1);
		pane.add(lb2);
		pane.add(lb3);
		pane.add(lb4);
		pane.add(lb5);
		pane.add(lb6);
		pane.add(lb7);
		pane.add(btnback);
		lb1.setBounds(20, 10, 300, 100);
		lb2.setBounds(70,10, 300, 100);
		lb3.setBounds(150,10, 300, 100);
		lb4.setBounds(240,10, 300, 100);
		lb5.setBounds(360,10, 300, 100);
		lb6.setBounds(450,10, 300, 100);
		lb7.setBounds(550,10, 300, 100);
		btnback.setBounds(200,300,140,30);
		btnback.setForeground(Color.black);
		btnback.setBackground(Color.yellow);
		btnback.addActionListener(this);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UserDAO udao = new UserDAO();
		
		int numberOfLabels = udao.getAllHistory().size();
		JLabel[] labels = new JLabel[numberOfLabels];
		
		for(int i=0; i<numberOfLabels; i++){
			String labelText = i+1+"             "+udao.getAllHistory().get(i).getUname()+"                 "+udao.getAllHistory().get(i).getUaccount()+"                      "+udao.getAllHistory().get(i).getUcontent()+"                     "+udao.getAllHistory().get(i).getUamount()+"               "+udao.getAllHistory().get(i).getUbalance()+"         "+udao.getAllHistory().get(i).getUdate();		
			labels[i] = new JLabel(labelText);
			pane.add(labels[i]);
			labels[i].setBounds(20, 40+i*15, 1200, 100);
		}
		
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnback){
			main panel=new main();
			
			panel.setSize(550,1000);
			panel.setVisible(true);
			panel.setResizable(true);
			panel.setLocation(350,250);
			dispose();
		}
	}
	
	public static void main(String[] args){
		history log=new history();
		
		log.setLocation(350,800);
		log.setSize(800,400);
		log.setTitle("ATM_관리자_최근거래내역");
		log.setResizable(false);
		log.setVisible(true);
		
	}
}
