package manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Server.ServerMaster;
import Server.UserDAO;

public class historyEx {

	UserDAO udao = new UserDAO();

	public ArrayList<String> history(){
		UserBean ub5 = new UserBean();
		ServerMaster server = new ServerMaster();
		ub5= server.getUb();
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> enter = new ArrayList<String>();
		enter.add("\n");
		String query = "select uname, uaccount, ucontent, uamount, ubalance, udate from history where uname = 'kimhyeonju'";
		
		try{
			udao.connect();
			ResultSet rs = udao.stmt.executeQuery(query);
			rs.last();      
		    int rowcount = rs.getRow();
		    rs.beforeFirst();
		    System.out.println("Total rows : " + rowcount);
			
			while(rs.next()){
				for(int j=1; j<=6; j++){
					list.add(rs.getString(j));
				}
				list.add("\n");
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args){
		historyEx h = new historyEx();
		System.out.println(h.history());		
		System.out.println("history 완료");
	}


}