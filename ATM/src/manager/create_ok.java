package manager;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import javax.swing.*;

public class create_ok extends JFrame implements ActionListener{
		
		
	
		JLabel lb = new JLabel("계좌가 생성되었습니다.");
		JButton btnOK = new JButton("확인");
		JPanel pane = new JPanel();
		public create_ok(){
			
			pane.setLayout(null);
			pane.add(lb);
			pane.add(btnOK);
			lb.setBounds(110, 50, 300, 100);
			btnOK.setBounds(150, 190, 70, 30);
	        btnOK.addActionListener(this);
			setContentPane(pane);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
		public void actionPerformed(ActionEvent e){
			Object source=e.getSource();
			
			if(source == btnOK){
				 dispose();
				
			}
			
		} 
		
		
		public static void main(String[] args){
			create_ok log=new create_ok();
			
			log.setLocation(400,250);
			log.setSize(400,300);
			log.setTitle("ATM_관리자_계좌생성성공");
			log.setResizable(false);
			log.setVisible(true);
		}
		
		
		
}
