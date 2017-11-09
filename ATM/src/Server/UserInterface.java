package Server;

import java.util.ArrayList;

import manager.UserBean;

public interface UserInterface {
	public void connect();//db와 연동
	public void disconnect();//db와 연동해제
	public String getAccount();//난수발생을 통해 중복없는 계좌번호생성
	public void createAccount();//계좌생성
	public String getUbalance();//사용자별 잔액조회
	public ArrayList<UserBean> getAllUser();//고객목록 가져오기
	public void setSum(int sum);//입금, 출금 후 사용자별 잔액저장
	public String getSum();//입금, 출금 후 사용자별 잔액 가져오기
	public boolean dep_trans();//입금-고객정보확인 후 입금 처리 성공 시 true return
	public void You_dep();//송금 시 송금받는사용자 입금 처리
	public boolean Wdraw_trans();//출금-고객정보확인 후 잔액확인 후 출금여부 가능 시 true return
	public boolean confirm_login();//고객정보확인 후 성공 시 true return
	public void You_info();//송금 시 송금받는사용자 비밀번호, 잔액 가져오기
	public boolean confirm_You_login();//송금 시 송금받는사용자 고객정보확인 후 성공 시 true return
	public int getHx();//history list 가져오기
	public void setHx(int hx);//history list 저장하기
	public ArrayList<String> history();//사용자별 history조회
	public ArrayList<UserBean> getAllHistory();//모든 사용자 history조회
	
}
