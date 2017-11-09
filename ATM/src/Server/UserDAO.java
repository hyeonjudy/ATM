package Server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import manager.UserBean;

public class UserDAO implements UserInterface{
	
	UserBean ub = new UserBean();
	public int MAX = 100;
	public int a = 0;
	public int b = 0;
	int sum;
	public int hx = 0;
	


	public String driver = "oracle.jdbc.driver.OracleDriver";
	public String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public String uid = "atm";
	public String upw = "atm123";
	
	public Connection dbcon;
	public Statement stmt;
	
	public void connect(){
		try{
			Class.forName(driver);
			dbcon = DriverManager.getConnection(url, uid, upw);
			stmt = dbcon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try{
			stmt.close();
			dbcon.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getAccount(){
		ArrayList<String> account = new ArrayList<String>();
		
		Random random = new Random();
		String uaccount = Integer.toString(random.nextInt(1000000000));
		account.add(uaccount);
		while(account.contains((String)uaccount)){
			uaccount = Integer.toString(random.nextInt(1000000000));
		}
		
		return uaccount;
	}
	
	public void createAccount(){
		
		UserBean ub1 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub1 = server.getUb();
		String query = "insert into atmuser(uname, uaccount, upw, udate, ucontent, uamount)"+ "values('"+ub1.getUname()+"','"+ub1.getUaccount()+"','"+ub1.getUpw()+"','"+getTime()+"','create','0')";
		String query2 = "insert into history values('"+ub1.getUname()+"','"+ub1.getUaccount()+"','"+ub1.getUpw()+"','create','0','0','"+getTime()+"')";
		try {
			connect();
			stmt.executeUpdate(query);
			stmt.executeUpdate(query2);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	
	}
	public String getUbalance(){
		UserBean ub4 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub4 = server.getUb();
		
		String query = "select ubalance from atmuser where uaccount = '"+ub4.getUaccount()+"'";
		
		try{
			connect();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				ub4.setUbalance(rs.getString("ubalance"));
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ub4.getUbalance();
	}
	
	public ArrayList<UserBean> getAllUser(){
		String query = "select uname, uaccount, ubalance from atmuser";
		
		ArrayList<UserBean> userlist = new ArrayList<UserBean>();
		try{
			connect();
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			int rowcount = rs.getRow();
			rs.beforeFirst();
			System.out.println("Total rows : "+rowcount);
			while(rs.next()){
				UserBean ub = new UserBean();
				ub.setUname(rs.getString("uname"));
				ub.setUaccount(rs.getString("uaccount"));
				ub.setUbalance(rs.getString("ubalance"));
				userlist.add(ub);
			}
			rs.close();
			disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userlist;
	
	}
	 
	public void setSum(int sum){
		this.sum = sum;
	}

	public String getSum(){
		return Integer.toString(sum);
	}

	public boolean dep_trans(){
		
		UserBean ub2 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub2 = server.getUb();
		server.setUb(ub2);
		if(confirm_login()==true){
			String query = "select * from atmuser where uaccount ='"+ub2.getUaccount()+"'";
			 	 
			try{
				connect();
			 	ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){				
					a = Integer.parseInt(rs.getString(6));
					b = Integer.parseInt(ub2.getUamount());
					sum = a + b;
					setSum(sum);
					System.out.println(getSum());
				}
				
			}catch(SQLException e1){
				e1.printStackTrace();
			}	   	
			   
			String query2 = "update atmuser set ucontent = 'dep', uamount = '"+ub2.getUamount()+"', ubalance = '"+getSum()+"', udate = '"+getTime()+"' where uaccount = '"+ub2.getUaccount()+"'";
			String query3 = "insert into history values('"+ub2.getUname()+"','"+ub2.getUaccount()+"','"+ub2.getUpw()+"','dep','"+ub2.getUamount()+"','"+getSum()+"','"+getTime()+"')";   	
			try{
				stmt.executeUpdate(query2);
				stmt.executeQuery(query3);
			}catch(SQLException e1){
				e1.printStackTrace();
			}return true;
		}else{
			System.out.println("입금실패");
			return false;
		}
	}
	
	public void You_dep(){
		UserBean ub6 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub6 = server.getUb();
		server.setUb(ub6);
		if(confirm_login()==true){
			String query = "select * from atmuser where uaccount ='"+ub6.getYaccount()+"'";
			 	 
			try{
				connect();
			 	ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){				
					a = Integer.parseInt(rs.getString(6));
					b = Integer.parseInt(ub6.getUamount());
					sum = a + b;
					setSum(sum);
					System.out.println(getSum());
				}
				
			}catch(SQLException e1){
				e1.printStackTrace();
			}	   	
			   
			String query2 = "update atmuser set ucontent = 'dep', uamount = '"+ub6.getUamount()+"', ubalance = '"+getSum()+"', udate = '"+getTime()+"' where uaccount = '"+ub6.getYaccount()+"'";
			You_info();
			ub6 = server.getUb();
			int YbalInt = Integer.parseInt(ub6.getUamount())+Integer.parseInt(ub6.getYbalance());
			String Ybal = Integer.toString(YbalInt);
			String query4 = "insert into history values('"+ub6.getYname()+"','"+ub6.getYaccount()+"','"+ub6.getYpw()+"','dep','"+ub6.getUamount()+"','"+Ybal+"','"+getTime()+"')";  	
			try{
				stmt.executeUpdate(query2);
				stmt.executeUpdate(query4);
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		}
	}
	
	
	
	public boolean Wdraw_trans(){
		
		UserBean ub3 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub3 = server.getUb();
		server.setUb(ub3);
		if(confirm_login()==true){
			String query = "select * from atmuser where uaccount ='"+ub3.getUaccount()+"'";
		 	 
		 	try{
		 		 connect();
		 		 ResultSet rs = stmt.executeQuery(query);
		 		 while(rs.next()){
					 a = Integer.parseInt(rs.getString(6));
					 b = Integer.parseInt(ub3.getUamount());
					 sum = a - b;
					 if(sum<0){
						 System.out.println("false줌줌");
						 return false;
					 }else{
						 setSum(sum);
						 System.out.println(getSum());
					 }
		 		 }
				 
				
			}catch(SQLException e1){
				e1.printStackTrace();
			}
		   	
		   	String query2 = "update atmuser set ucontent = 'Wdr', uamount = '"+ub3.getUamount()+"', ubalance = '"+getSum()+"', udate = '"+getTime()+"' where uaccount = '"+ub3.getUaccount()+"'";
		   	String query3 = "insert into history values('"+ub3.getUname()+"','"+ub3.getUaccount()+"','"+ub3.getUpw()+"','wdr','"+ub3.getUamount()+"','"+getSum()+"','"+getTime()+"')";
		    try{
		    	stmt.executeUpdate(query2);
		    	stmt.executeQuery(query3);
			}catch(SQLException e1){
				e1.printStackTrace();
			}return true;
		}else{
			System.out.println("출금실패");
			return false;
		}
	}
	 
	 //이용하기 - 잔액조회, 입금, 출금, 송금, 최근내역조회
	public boolean confirm_login(){
		UserBean ubc = new UserBean();
		ServerMaster server = new ServerMaster();
		ubc = server.getUb();
		String query = "select * from atmuser where uname = '"+ubc.getUname()+"' AND upw = '"+ubc.getUpw()+"' AND uaccount = '"+ubc.getUaccount()+"'";
		
		try{
			connect();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()){
				return true;
			}else{
				System.out.println("false 처리됨");
				return false;
				
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public void You_info(){
		UserBean ubYI = new UserBean();
		ServerMaster server = new ServerMaster();
		ubYI = server.getUb();
		
		String query = "select * from atmuser where uname = '"+ubYI.getYname()+"'";
		
		try{
			connect();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				ubYI.setYpw(rs.getString("upw"));
				ubYI.setYbalance(rs.getString("ubalance"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		server.setUb(ubYI);
	}
	
	
	public boolean confirm_You_login(){
		UserBean ubcY = new UserBean();
		ServerMaster server = new ServerMaster();
		ubcY = server.getUb();
		
		String query = "select * from atmuser where uname = '"+ubcY.getYname()+"' AND  uaccount = '"+ubcY.getYaccount()+"'";
		
		try{
			connect();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()){
				return true;
			}else{
				System.out.println("false 처리됨");
				return false;
				
			}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	

	public int getHx() {
		return hx;
	}

	public void setHx(int hx) {
		this.hx = hx;
	}
	
	
	public ArrayList<String> history(){
		UserBean ub5 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub5= server.getUb();
		
		ArrayList<String> list = new ArrayList<String>();
		
		String query = "select uname, uaccount, ucontent, uamount, ubalance, udate from history where uaccount = '"+ub5.getUaccount()+"'";
		
		try{
			connect();
			ResultSet rs = stmt.executeQuery(query);
			rs.last();      
		    int rowcount = rs.getRow();
		    rs.beforeFirst();
		    System.out.println("Total rows : " + rowcount);
			setHx(rowcount);
			while(rs.next()){
				for(int j=1; j<=6; j++){
					list.add(rs.getString(j));
				}
			}return list;
		}catch(SQLException e){
			e.printStackTrace();
		}return list;
		
	}
	
	public ArrayList<UserBean> getAllHistory(){
		String query = "select uname, uaccount, ucontent, uamount, ubalance, udate from history";
		
		ArrayList<UserBean> userlist = new ArrayList<UserBean>();
		try{
			connect();
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			int rowcount = rs.getRow();
			rs.beforeFirst();
			System.out.println("Total rows : "+rowcount);
			while(rs.next()){
				UserBean ub = new UserBean();
				ub.setUname(rs.getString("uname"));
				ub.setUaccount(rs.getString("uaccount"));
				ub.setUcontent(rs.getString(3));
				ub.setUamount(rs.getString(4));
				ub.setUbalance(rs.getString("ubalance"));
				ub.setUdate(rs.getString(6));
				userlist.add(ub);
			}
			rs.close();
			disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return userlist;
	
	}
	
	
	 static String getTime() {
			SimpleDateFormat f = new SimpleDateFormat("[yy-MM-dd:hh:mm:ss]");//mm이 이상하게 나오는거 고치기!!!!!!!!!!!!!!!!!!!!!!
			return f.format(new Date());
	}

}