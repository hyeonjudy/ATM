package manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class main extends JFrame implements ActionListener{
	
	public static void main(String[] args){
		
		main panel=new main();
		
		panel.setSize(550,700);
		panel.setVisible(true);
		panel.setResizable(true);
		panel.setLocation(350,250);
		
	}
	

	JButton btn1 = new JButton("계좌생성 승인");
	JButton btn2 = new JButton("계좌생성");
	JButton btn3 = new JButton("고객목록");
	JButton btn4 = new JButton("거래내역조회");
	JButton btn5 = new JButton("입금");
	JButton btn6 = new JButton("출금");
	JButton btn7 = new JButton("이체");
	
	
	
	public main(){
		super("ATM_관리자");
		
		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.add(btn1);
		pane.add(btn2);
		pane.add(btn3);
		pane.add(btn4);
		pane.add(btn5);
		pane.add(btn6);
		pane.add(btn7);
		
		btn1.setBounds(80,100,150,50);
		btn1.addActionListener(this);
		btn5.setBounds(300,100,150,50);
		btn5.addActionListener(this);
		btn2.setBounds(80,250,150,50);
		btn2.addActionListener(this);
		btn6.setBounds(300,250,150,50);
		btn6.addActionListener(this);
		btn3.setBounds(80,400,150,50);
		btn3.addActionListener(this);
		btn7.setBounds(300,400,150,50);
		btn7.addActionListener(this);
		btn4.setBounds(180,550,150,50);
		btn4.addActionListener(this);
		
		
		btn1.setForeground(Color.black);
		btn1.setBackground(Color.yellow);
		btn2.setForeground(Color.black);
		btn2.setBackground(Color.yellow);
		btn3.setForeground(Color.black);
		btn3.setBackground(Color.yellow);
		btn4.setForeground(Color.black);
		btn4.setBackground(Color.yellow);
		btn5.setForeground(Color.black);
		btn5.setBackground(Color.yellow);
		btn6.setForeground(Color.black);
		btn6.setBackground(Color.yellow);
		btn7.setForeground(Color.black);
		btn7.setBackground(Color.yellow);
		
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		
		Object source = e.getSource();
		UserBean ub = new UserBean();
		if(source == btn1){
			create_acc_accept log = new create_acc_accept();
			
			log.setLocation(350,250);
			log.setSize(600,400);
			log.setTitle("ATM_관리자_계좌생성승인");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}
		else if(source == btn2){
			create_acc log=new create_acc();
			
			log.setLocation(350,250);
			log.setSize(600,400);
			log.setTitle("ATM_관리자_계좌생성");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}
		else if(source == btn3){
			userList panel = new userList();
			
			panel.setLocation(350,250);
			panel.setSize(600,400);
			panel.setTitle("ATM_관리자_고객목록");
			panel.setResizable(false);
			panel.setVisible(true);
			dispose();
		}
		else if(source == btn4){
			history log=new history();
			
			log.setLocation(350,800);
			log.setSize(800,400);
			log.setTitle("ATM_관리자_최근거래내역");
			log.setResizable(false);
			log.setVisible(true);
		}
		else if(source == btn5){
			dep_trans log = new dep_trans();

			log.setLocation(400, 250);
			log.setSize(250, 240);
			log.setResizable(true);
			log.setVisible(true);
			dispose();
		}
		else if(source == btn6){
			wdr_trans log = new wdr_trans();

			log.setLocation(400, 250);
			log.setSize(250, 240);
			log.setResizable(false);
			log.setVisible(true);
			dispose();
		}
		else if(source == btn7){
			tran_trans log = new tran_trans();
			
			log.setLocation(400, 250);
			log.setSize(500, 280);
			log.setResizable(true);
			log.setVisible(true);
			dispose();
		}
		
	
	}
	
	
}

