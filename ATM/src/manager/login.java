package manager;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import javax.swing.*;







public class login extends JFrame implements ActionListener{
	
	JTextField managerid=new JTextField(25);
	JPasswordField managerpw=new JPasswordField(25);
	JLabel lb1=new JLabel("ManagerID: ");
	JLabel lb2=new JLabel("ManagerPW: ");
	JButton btnOk=new JButton("확인");
	
	Connection dbcon;
	Statement stmt;
	ResultSet rs;
	
	String url = "oracle.jdbc.driver.OracleDriver";
	String info = "jdbc:oracle:thin:@localhost:1521:xe";
	String dname = "atm";
	String dpw = "atm123";
	
	public login(){
		JPanel pane = new JPanel();
		pane.setLayout(null);
		
		pane.add(managerid);
		pane.add(managerpw);
		pane.add(lb1);
		pane.add(lb2);
		pane.add(btnOk);
		
		managerid.setBounds(100,20,100,20);
		managerpw.setBounds(100,40,100,20);
		lb1.setBounds(10,20,80,20);
		lb2.setBounds(10,40,80,20);
		btnOk.setBounds(50,70,75,20);
		btnOk.addActionListener(this);
		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			Class.forName(url);
			dbcon = DriverManager.getConnection(info, dname, dpw);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("db접근 성공!");

	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnOk){
			try{
				String str1=managerid.getText();
				String str2=managerpw.getText();
				if((str1.length()==0 || str2.length()==0)){
    					JOptionPane.showMessageDialog(null,"Connot be Process","WARNING",JOptionPane.WARNING_MESSAGE);
    				}
    				else{
				stmt=dbcon.createStatement();
				String strUser="";
				String strPass="";
				
				ResultSet rs=stmt.executeQuery("SELECT * FROM atmmanager WHERE id='"+str1+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(2);
				}
				
				
				
				
				
				stmt.close();
		
				if(strUser.equals(str1)){
					if(strPass.equals(str2)){
						
					
    					
						JOptionPane.showMessageDialog(null,"Welcome "+managerid.getText(),"Welcome",JOptionPane.INFORMATION_MESSAGE);
						
						main panel=new main();
						
						panel.setSize(550,700);
						panel.setVisible(true);
						panel.setResizable(true);
						panel.setLocation(350,250);
						
					
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the password is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						managerpw.requestFocus(true);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
					managerid.requestFocus(true);
				}
    				}	
			}catch(SQLException w){
			}
		}
	}

	
	public static void main(String[]args){
		login log=new login();
		
		log.setLocation(400,250);
		log.setSize(250,150);
		log.setTitle("ATM_관리자_Log-In");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}
