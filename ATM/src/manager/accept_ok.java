package manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class accept_ok extends JFrame implements ActionListener{
		
		
	
		JLabel lb = new JLabel("위고객의 계좌생성이 완료되었습니다.");
		JButton btnOK = new JButton("확인");
		
		public accept_ok(){
			JPanel pane = new JPanel();
			pane.setLayout(null);
			pane.add(lb);
			pane.add(btnOK);
			lb.setBounds(90, 50, 300, 100);
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
			accept_ok log=new accept_ok();
			
			log.setLocation(400,250);
			log.setSize(400,300);
			log.setTitle("ATM_관리자_계좌생성승인성공");
			log.setResizable(false);
			log.setVisible(true);
		}
		
		
		
}
