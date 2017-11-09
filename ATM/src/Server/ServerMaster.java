package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import manager.UserBean;


import manager.create_acc_accept;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;

public class ServerMaster {
		
		static UserBean ub = new UserBean();
		public static String server1IP = "192.168.100.40";
		public static String server2IP = "192.168.100.40";
		public static final int server1portnum = 9999;
		public static final int server2portnum = 9999;
		
		public static void setUb(UserBean ubb){
			
			ub = ubb;
		}
		
		public static UserBean getUb(){
			return ub;
		}
		
		public static void cre_soc1(){
			try {
				Socket socket1 = new Socket(server1IP, server1portnum);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(server1IP + " 서버에 연결중입니다");
			System.out.println("소켓 생성 완료");
		}
		
		public static void cre_soc2(){
			try {
				Socket socket2 = new Socket(server2IP, server2portnum);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(server2IP + " 서버에 연결중입니다");
			System.out.println("소켓 생성 완료");
		}
		
		public static void main(String[] args) throws SQLException{
		
		UserBean ub = new UserBean();
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream in = null;
		DataInputStream dis = null;
		
		UserDAO udao = new UserDAO();
		udao.connect();
		
		try{
			
			serverSocket = new ServerSocket(9999);
			System.out.println(getTime() + " 연결요청을 기다립니다.");
			socket = serverSocket.accept();
			System.out.println(getTime() + socket.getInetAddress() + " 로부터 연결요청이 들어왔습니다.");
			
			in = socket.getInputStream();
			dis = new DataInputStream(in);
			DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
			
			String inputMessage;
			String outputMessage;
			
			ArrayList<String> list = new ArrayList<String>();
			
			
			
			
			
			
			while(true){
				inputMessage = dis.readUTF();
				if(inputMessage.equalsIgnoreCase("EXIT")){
					break;
				}
				System.out.println(inputMessage);
				list.add(inputMessage);
	            
			}
			//(고객요청)     1. 계좌생성   2. 입금   3. 출금   4. 잔액조회   5. 최근거래내역조회   6. 송금     
			//(history table) 1, 2, 3, 6
			
			if(list.get(0).equalsIgnoreCase("1")){
				UserBean ub1 = new UserBean();
				ub1.setUdate(getTime());
				ub1.setUname(list.get(1));
				ub1.setUpw(list.get(2));
				ub1.setUaccount(udao.getAccount());
				setUb(ub1);
				
				int acc = Integer.parseInt(ub1.getUaccount());
				if(acc % 3 == 0){
					create_acc_accept panel = new create_acc_accept();
					
					panel.setLocation(350,250);
					panel.setSize(600,400);
					panel.setTitle("ATM_관리자_계좌생성승인");
					panel.setResizable(false);
					panel.setVisible(true);
					
					dos.writeUTF(ub1.getUaccount());
					System.out.println("전송완료");
				}else if(acc % 3 == 1){
					socket.close();
					cre_soc1();
					
					dos.writeUTF("1");
					dos.writeUTF(ub1.getUname());
					dos.writeUTF(ub1.getUpw());
					dos.writeUTF("EXIT");
				}else{
					socket.close();
					cre_soc2();
					
					dos.writeUTF("1");
					dos.writeUTF(ub1.getUname());
					dos.writeUTF(ub1.getUpw());
					dos.writeUTF("EXIT");
				}
			}
			if(list.get(0).equalsIgnoreCase("2")){
				UserBean ub2 = new UserBean();
				ub2.setUname(list.get(1));
				ub2.setUaccount(list.get(2));
				ub2.setUpw(list.get(3));
				ub2.setUamount(list.get(4));
				setUb(ub2);
				
				int acc = Integer.parseInt(ub2.getUaccount());
				
				if(acc%3 == 0){
					dos.writeBoolean(udao.dep_trans());
				    dos.writeUTF(udao.getSum());
				    System.out.println("전송완료");
				}else if(acc % 3 ==1){
					socket.close();
					cre_soc1();
					
					dos.writeUTF("2");
					dos.writeUTF(ub2.getUname());
					dos.writeUTF(ub2.getUaccount());
					dos.writeUTF(ub2.getUpw());
					dos.writeUTF(ub2.getUamount());
					dos.writeUTF("EXIT");
				}else{
					socket.close();
					cre_soc2();
					
					dos.writeUTF("2");
					dos.writeUTF(ub2.getUname());
					dos.writeUTF(ub2.getUaccount());
					dos.writeUTF(ub2.getUpw());
					dos.writeUTF(ub2.getUamount());
					dos.writeUTF("EXIT");
				}
			    
			}
			if(list.get(0).equalsIgnoreCase("3")){
				UserBean ub3 = new UserBean();
				ub3.setUname(list.get(1));
				ub3.setUaccount(list.get(2));
				ub3.setUpw(list.get(3));
				ub3.setUamount(list.get(4));
				setUb(ub3);
				
				int acc = Integer.parseInt(ub3.getUaccount());
				
				if(acc % 3 == 0){
					dos.writeBoolean(udao.Wdraw_trans());
				    dos.writeUTF(udao.getSum());
				    System.out.println("전송완료");
				}else if(acc % 3 == 1){
					socket.close();
					cre_soc1();
					
					dos.writeUTF("3");
					dos.writeUTF(ub3.getUname());
					dos.writeUTF(ub3.getUaccount());
					dos.writeUTF(ub3.getUpw());
					dos.writeUTF(ub3.getUamount());
					dos.writeUTF("EXIT");
				}else{
					socket.close();
					cre_soc2();
					
					dos.writeUTF("3");
					dos.writeUTF(ub3.getUname());
					dos.writeUTF(ub3.getUaccount());
					dos.writeUTF(ub3.getUpw());
					dos.writeUTF(ub3.getUamount());
					dos.writeUTF("EXIT");
				}
			}
			if(list.get(0).equalsIgnoreCase("4")){
				UserBean ub4 = new UserBean();
				ub4.setUname(list.get(1));
				ub4.setUaccount(list.get(2));
				ub4.setUpw(list.get(3));
				setUb(ub4);
				
				int acc = Integer.parseInt(ub4.getUaccount());
				
				if(acc%3==0){
					if(udao.confirm_login()==false){
						dos.writeBoolean(false);
					}else{
						dos.writeBoolean(udao.confirm_login());
						dos.writeUTF(udao.getUbalance());
					}
					System.out.println("전송완료");
				}else if(acc%3==1){
					socket.close();
					cre_soc1();
					
					dos.writeUTF("4");
					dos.writeUTF(ub4.getUname());
					dos.writeUTF(ub4.getUaccount());
					dos.writeUTF(ub4.getUpw());
					dos.writeUTF("EXIT");
				}else{
					socket.close();
					cre_soc2();
					
					dos.writeUTF("4");
					dos.writeUTF(ub4.getUname());
					dos.writeUTF(ub4.getUaccount());
					dos.writeUTF(ub4.getUpw());
					dos.writeUTF("EXIT");
				}
			}
			if(list.get(0).equalsIgnoreCase("5")){
				UserBean ub5 = new UserBean();
				ub5.setUname(list.get(1));
				ub5.setUaccount(list.get(2));
				ub5.setUpw(list.get(3));
				setUb(ub5);
				
				int acc = Integer.parseInt(ub5.getUaccount());
				
				if(acc%3==0){
					if(udao.confirm_login()==true){
						dos.writeBoolean(true);
						ArrayList<String> resultlist = new ArrayList<String>();
						resultlist.addAll(udao.history());
						dos.writeInt(udao.getHx());
						System.out.println(udao.getHx()+"줌");
						for(int i=0; i<resultlist.size(); i++){
							dos.writeUTF(resultlist.get(i));
							System.out.println(resultlist.get(i));
						}
					}else{
						dos.writeBoolean(false);
					}
					System.out.println("전송완료");
				}else if(acc%3==1){
					socket.close();
					cre_soc1();
					
					dos.writeUTF("5");
					dos.writeUTF(ub5.getUname());
					dos.writeUTF(ub5.getUaccount());
					dos.writeUTF(ub5.getUpw());
					dos.writeUTF("EXIT");
				}else{
					socket.close();
					cre_soc2();
					
					dos.writeUTF("5");
					dos.writeUTF(ub5.getUname());
					dos.writeUTF(ub5.getUaccount());
					dos.writeUTF(ub5.getUpw());
					dos.writeUTF("EXIT");
				}
			}
			if(list.get(0).equalsIgnoreCase("6")){
				UserBean ub6 = new UserBean();
				ub6.setUname(list.get(1));
				ub6.setUaccount(list.get(2));
				ub6.setUpw(list.get(3));
				ub6.setUamount(list.get(4));
				ub6.setYname(list.get(5));
				ub6.setYaccount(list.get(6));
				setUb(ub6);
				
				int accS = Integer.parseInt(ub6.getUaccount());
				int accR = Integer.parseInt(ub6.getYaccount());
				
				if(accS%3==0 && accR%3==0){
					boolean boolu = udao.confirm_login();
					boolean booly = udao.confirm_You_login();
					if(boolu==true&&booly==true){//로그인 성공시
						
						dos.writeBoolean(true);//true
						System.out.println("true줌");
						
						
						if(udao.Wdraw_trans()==true){
							dos.writeBoolean(true);
						    dos.writeUTF(udao.getSum());//클라이언트에게 잔액주기
						    udao.You_dep();//You는 입금
						}
					    
					}else{
						dos.writeBoolean(false);//false false 
						dos.writeBoolean(false);
					}
				}else if(accS%3==1 && accR%3==1){
					socket.close();
					cre_soc1();
					
					dos.writeUTF("6");
					dos.writeUTF(ub6.getUname()); dos.writeUTF(ub6.getUaccount()); dos.writeUTF(ub6.getUpw()); dos.writeUTF(ub6.getUamount());
					dos.writeUTF(ub6.getYname()); dos.writeUTF(ub6.getYaccount());
					dos.writeUTF("EXIT");
				}else if(accS%3==2 && accR%3==2){
					socket.close();
					cre_soc2();
					
					dos.writeUTF("6");
					dos.writeUTF(ub6.getUname()); dos.writeUTF(ub6.getUaccount()); dos.writeUTF(ub6.getUpw()); dos.writeUTF(ub6.getUamount());
					dos.writeUTF(ub6.getYname()); dos.writeUTF(ub6.getYaccount());
					dos.writeUTF("EXIT");
				}
				
			}
			if(list.get(0).equalsIgnoreCase(null)){
				dos.writeUTF(udao.getAccount());
			}
			
			dos.close();
			
		
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}finally{
			try{
				dis.close();
				socket.close();
				serverSocket.close();
				}catch(IOException e){
					System.out.println("클라이언트와 채팅중 오류가 발생했습니다.");
				}
			}
	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[yy-mm-dd:hh:mm:ss]");
		return f.format(new Date());
	}
}
