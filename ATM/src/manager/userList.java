package manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.UserDAO;

import java.util.ArrayList;


public class userList extends JFrame implements ActionListener{
	
	
	
	JLabel lb1 = new JLabel("번호");
	JLabel lb2 = new JLabel("고객명");
	JLabel lb3 = new JLabel("계좌번호");
	JLabel lb4 = new JLabel("잔액");
	

	JButton btnback = new JButton("메인화면으로");
	
	

	public userList(){
		JPanel pane = new JPanel();
		
		pane.setLayout(null);
		
		pane.add(lb1);
		pane.add(lb2);
		pane.add(lb3);
		pane.add(lb4);
		pane.add(btnback);
		
		UserDAO udao = new UserDAO();
		ArrayList<UserBean> userlist = new ArrayList<UserBean>();
		userlist = udao.getAllUser();
		
		UserBean user = new UserBean();
		for(int i=0; i<userlist.size(); i++){
			user = userlist.get(i);
			//System.out.println(user.getUname()+user.getUaccount()+user.getUbalance());
		}
		
		//JLabel array/forloop 출력
		
		
		lb1.setBounds(50, 10, 300, 100);
		lb2.setBounds(120,10, 300, 100);
		lb3.setBounds(230,10, 300, 100);
		lb4.setBounds(350, 10, 300, 100);
		btnback.setBounds(200, 300, 140, 30);
		btnback.setForeground(Color.black);
		btnback.setBackground(Color.yellow);
		btnback.addActionListener(this);
		
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int numberOfLabels = udao.getAllUser().size();
		JLabel[] labels = new JLabel[numberOfLabels];
		
		for(int i=0; i<numberOfLabels; i++){
			String labelText = i+1+"                    "+udao.getAllUser().get(i).getUname()+"                   "+udao.getAllUser().get(i).getUaccount()+"                    "+udao.getAllUser().get(i).getUbalance();
			labels[i] = new JLabel(labelText);
			pane.add(labels[i]);
			labels[i].setBounds(50, 50+i*15, 800, 100);
		}
		
		
		
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
	}	
	
	public static void main(String[] args){
	
		userList panel = new userList();
		panel.setLocation(350,250);
		panel.setSize(600,400);
		panel.setTitle("ATM_관리자_고객목록");
		panel.setResizable(false);
		panel.setVisible(true);
		
	}
}
