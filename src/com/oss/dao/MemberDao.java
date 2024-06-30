package com.oss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.oss.model.Member;
import com.oss.model.Process;
import com.oss.model.TimeRecord;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection conn; // DB 연결 객체
	private PreparedStatement pstmt; // Query 작성 객체
	private ResultSet rs; // Query 결과 커서
	
	// DB 자원연결 반납
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close(); // 유효성검사 후 자원 반납
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("DB close");
            e.printStackTrace();
        }
 
    }
	
	// 사용자 아이디 중복 여부 확인
	// 매개변수로 넣은 userid가 MEMBER 테이블에 이미 존재한다면 false, 아니라면 true, 없으면 DB반납
	public boolean checkID(String userid) {
		// DB 연결
		conn = DBConnection.getConnection();
		
		try {
			// Query 작성
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE USERID = ?");
			
			// Query 완성 (index 1번 부터 시작)
			pstmt.setString(1, userid);
			

			// Query 실행
			rs = pstmt.executeQuery(); // return값은 처리된 레코드의 개수
			
			if(rs.next()) { // next()함수는 커서를 한칸 내리면서 해당 행에 데이터가 있으면 true, 없으면 false 반환
				return false; // 중복 여부 확인 실패 (해당 테이블에 이미 userid에 대해 같은 값이 있으므로)
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return true; // 중복 여부 확인 성공
	}
	
	// 사용자 정보로 회원가입
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean saveUserInfo(Member member) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO MEMBER(USERID, PASSWORD, NAME) VALUES(?,?,?)");
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
			
			return true; // 회원가입 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false; // 회원가입 실패
	}
	
	// 사용자 로그인
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean findByUserIDAndPassword(String userid, String password) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE USERID = ? AND PASSWORD = ?");
			
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 사용자 정보 가져오기
	// 성공 true, 실패 false, 없으면 DB반납
	public Member getUserInfo(Member member, String userid) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE USERID = ? AND NAME = ?");
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member.setUserid(rs.getString("userid"));
				member.setName(rs.getString("name"));
			}
 		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return member;
	}
	
	
	// 회원 탈퇴시, 사용자 비밀번호 확인
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean checkPassword(String userid, String password) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE USERID = ? AND PASSWORD = ?");
			
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 비밀번호 변경
	// 성공 비밀번호, 실패 false, 없으면 DB반납
	public boolean updatePassword(String userid, String previousPassword, String password) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("UPDATE MEMBER SET PASSWORD = ? WHERE USERID = ? AND PASSWORD = ?");
			
			pstmt.setString(1, password);
			pstmt.setString(2, userid);
			pstmt.setString(3, previousPassword);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 사용자 회원 탈퇴
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean deleteUser(String userid) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("DELETE MEMBER WHERE USERID = ?");
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("DROP TABLE " + userid +"PRGRESS");
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("DROP TABLE " + userid +"TIMERECORD");
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 사용자 사용기록 테이블 생성
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean createProcessTable(String userid) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("CREATE TABLE " + userid + "PROCESS(PROGRAMNAME VARCHAR2(100), TABNAME VARCHAR2(300), STARTTIME VARCHAR2(30) PRIMARY KEY NOT NULL)");
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 사용자 사용기록 테이블 삭제
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean deleteProcessTable(String userid) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("DELETE " + userid + "PROCESS");
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 사용자 사용시간 테이블 생성
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean createTimeRecordTable(String userid) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("CREATE TABLE " + userid + "TIMERECORD(PROGRAMNAME VARCHAR2(100), RECORDEDDATE VARCHAR2(30) NOT NULL, USAGETIME NUMBER(6), CREATEDATE TIMESTAMP(6) PRIMARY KEY NOT NULL)");
			pstmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 사용자 샘플 사용시간 저장
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean saveSampleTimeRecord(String userid) {
		conn = DBConnection.getConnection();
    	Date date_now = new Date(System.currentTimeMillis());
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO " + userid + "TIMERECORD(PROGRAMNAME, USAGETIME, RECORDEDDATE, CREATEDATE) VALUES(?,?,?,SYSDATE)");
			pstmt.setString(1, "Window Time");
			pstmt.setLong(2, 1);
			pstmt.setString(3, date_format.format(date_now));
			pstmt.executeUpdate();
			
			return true; // 사용기록 저장 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false; // 사용기록 저장 실패
	}
	
	// 사용자 사용기록 저장
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean saveProcessRecord(String userid, Process process) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO " + userid + "PROCESS(PROGRAMNAME, TABNAME, STARTTIME) VALUES(?,?,?)");
			pstmt.setString(1, process.getProgramName());
			pstmt.setString(2, process.getTabName());
			pstmt.setString(3, process.getStartTime());
			pstmt.executeUpdate();
			
			return true; // 사용기록 저장 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false; // 사용기록 저장 실패
	}
	
	// 사용자 사용시간 저장
	// 성공 true, 실패 false, 없으면 DB반납
	public boolean saveTimeRecord(String userid, String programName, Long usageTime, String recordedDate) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO " + userid + "TIMERECORD(PROGRAMNAME, USAGETIME, RECORDEDDATE, CREATEDATE) VALUES(?,?,?,SYSDATE)");
			pstmt.setString(1, programName);
			pstmt.setLong(2, usageTime);
			pstmt.setString(3, recordedDate);
			pstmt.executeUpdate();
			
			return true; // 사용기록 저장 성공
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false; // 사용기록 저장 실패
	}
	
	// 사용 기록 리스트로 가져오기
	// 성공 Vector<Process>, 실패 null
	public Vector<Process> findByAllProcess(String userid){
		conn = DBConnection.getConnection();
		Vector<Process> processes = new Vector<>();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM " + userid + "PROCESS");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Process process = new Process();
				process.setProgramName(rs.getString("PROGRAMNAME"));
				process.setTabName(rs.getString("TABNAME"));
				process.setStartTime(rs.getString("STARTTIME"));
				processes.add(process);
			}
			return processes;
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}

		return null;
	}
	
	// 특정 날짜의 특정 프로그램 조회
	// 성공 true, 실패 false, 없으면 DB 반납
	public boolean checkTimeRecord(String userid, String programName, String recordedDate) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM " + userid + "TIMERECORD WHERE PROGRAMNAME = ? AND RECORDEDDATE = ?");
			
			pstmt.setString(1, programName);
			pstmt.setString(2, recordedDate);
			
			rs = pstmt.executeQuery(); // return값은 처리된 레코드의 개수
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false; // 중복 여부 확인 성공
	}
	
	// 특정 날짜의 특정 프로그램 조회 및 시간 반환
	// 성공 true, 실패 false, 없으면 DB 반납
	public Long getTimeRecord(String userid, String programName, String recordedDate) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM " + userid + "TIMERECORD WHERE PROGRAMNAME = ? AND RECORDEDDATE = ?");
			
			pstmt.setString(1, programName);
			pstmt.setString(2, recordedDate);
			
			rs = pstmt.executeQuery(); // return값은 처리된 레코드의 개수
			
			if(rs.next()) { // next()함수는 커서를 한칸 내리면서 해당 행에 데이터가 있으면 true, 없으면 false 반환
				return rs.getLong("USAGETIME"); // 중복 여부 확인 성공 (해당 테이블에 이미 programName, recordedDate에 대해 같은 값이 있으므로)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return (long) 0; // 중복 여부 확인 실패
	}
	
	// 특정 날짜의 특정 프로그램 시간 업데이트
	// 성공 true, 실패 false, 없으면 DB 반납
	public boolean updateTimeRecord(String userid, String programName, String recordedDate, Long usageTime) {
		conn = DBConnection.getConnection();
		
		try {
			pstmt = conn.prepareStatement("UPDATE " + userid + "TIMERECORD SET USAGETIME = ? WHERE PROGRAMNAME = ? AND RECORDEDDATE = ?");
			pstmt.setLong(1, usageTime);
			pstmt.setString(2, programName);
			pstmt.setString(3, recordedDate);
			pstmt.executeUpdate();
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}
		
		return false;
	}
	
	// 특정 날짜의 모든 값 가져오기
	// 성공 Vector<TimeRecord>, 실패 null
	public Vector<TimeRecord> findByAllTimeRecord(String userid, String recordedDate){
		conn = DBConnection.getConnection();
		Vector<TimeRecord> timeRecords = new Vector<>();
		try {
			pstmt = conn.prepareStatement("SELECT * FROM " + userid + "TIMERECORD WHERE RECORDEDDATE = ?");
			pstmt.setString(1, recordedDate);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TimeRecord timeRecord = new TimeRecord();
				timeRecord.setProgramName(rs.getString("PROGRAMNAME"));
				timeRecord.setUsageTime(rs.getLong("USAGETIME"));
				timeRecord.setRecordedDate(rs.getString("RECORDEDDATE"));
				timeRecords.add(timeRecord);
			}
			return timeRecords;
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeAll(conn, pstmt, rs);
		}

		return null;
	}
}
