import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public class main {
	
	// Window 클래스형 ArrayList
	// 프로그램 명, 탭 명, 시작 시간이 들어감
	static ArrayList<Window> windows = new ArrayList<Window>();

	public static void main(String[] args) {
		
		GetWindowProcess gwp = new GetWindowProcess(windows);
		gwp.start();
		
		try {
			gwp.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[][] wList = new String[windows.size()][3];
		String w = "/";
		if(windows != null) {
			for(int i = 0;i < windows.size();i++) {
				wList[i][0] = windows.get(i).getProgramName();
				wList[i][1] = windows.get(i).getTabName();
				wList[i][2] = windows.get(i).getStartTime();
			}
		}

		for(int j = 0;j < wList.length;j++) {
			w += wList[j][0] + wList[j][1] + "/";
		}
		
		for(int k = 0;k < wList.length;k++) {
			System.out.println(wList[k][0] + "\n" + wList[k][1] + "\n" + wList[k][2] + "\n\n");
		}
	}
}

class GetWindowProcess extends Thread {
	ArrayList<Window> windows = new ArrayList<Window>();
	long start; // 시작 시간
	long end;   // 종료 시간
	
	GetWindowProcess(ArrayList<Window> windows){
		this.windows = windows;
	}
	
	public void run() {
		try {
			Window firstWindow = new Window();
			String processInfo;
			String[] ar;
			String tabName = "";
			String programName;
			char[] windowText = new char[512];
			
			start = System.currentTimeMillis();
			
	    	Date date_now = new Date(System.currentTimeMillis());
			SimpleDateFormat simple_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			// 최상단 프로그램 정보
			PointerType hwnd = User32.INSTANCE.GetForegroundWindow(); // then you can call it!
			User32.INSTANCE.GetWindowTextW(hwnd, windowText, 512);

			// split을 해 프로세스 정보에서 프로세스 이름만을 출력
		    processInfo = Native.toString(windowText);
		    ar = processInfo.split(" - ");
		    programName = ar[ar.length-1];
		    
		    // 탭 명
		    for(int i = 0;i < ar.length - 1;i++) {
		    	tabName += ar[i];
		  	  	if(i == ar.length - 2)
		  	  		break;
		    	tabName += " - ";
		    }
		    
		    // Window형 객체인 window에 값 넣어준 뒤, Window형 ArrayList인 windows에 넣어줌
		    firstWindow.setProgramName(programName);
		    firstWindow.setTabName(tabName);
		    firstWindow.setStartTime(simple_format.format(date_now));
		    windows.add(firstWindow);
		    
		    System.out.println(programName);
		    System.out.println(tabName);
		    System.out.println(simple_format.format(date_now) + "\n");
		    
		    Thread.sleep(1000);
			
		    
		    int j = 0;
		    while(j < 10) {

		    	tabName = "";
		    	
		    	// 현재 시간 정보
		    	date_now = new Date(System.currentTimeMillis());
				
				// 최상단 프로그램 정보
		    	hwnd = User32.INSTANCE.GetForegroundWindow();
				User32.INSTANCE.GetWindowTextW(hwnd, windowText, 512);
				
				// split을 해 프로세스 정보에서 프로세스 이름만을 출력
			    processInfo = Native.toString(windowText);
			    ar = processInfo.split(" - ");
			    programName = ar[ar.length-1];
			    
			    // 탭 명
			    for(int i = 0;i < ar.length - 1;i++) {
			    	tabName += ar[i];
			  	  	if(i == ar.length - 2)
			  	  		break;
			    	tabName += " - ";
			    }
			    
			    if(programName.equals(windows.get(windows.size() - 1).getProgramName())
			    		&& tabName.equals(windows.get(windows.size() - 1).getTabName())) {
				    // 1초 주기로 반복
				    Thread.sleep(1000);
				    j++;
				    
			    	continue;
			    }
			    
				// end - start는 ConnectionTime 요청을 받은 시점의 시간에서 처음 연결된 시간을 뺀 값
				end = System.currentTimeMillis();
			    
		    	Window window = new Window();
		    	
			    // Window형 객체인 window에 값 넣어준 뒤, Wind ow형 ArrayList인 windows에 넣어줌
			    window.setProgramName(programName);
			    window.setTabName(tabName);
			    window.setStartTime(simple_format.format(date_now));
			    windows.add(window);
			    
			    System.out.println(programName);
			    System.out.println(tabName);
			    System.out.println(simple_format.format(date_now) + "\n");
			    
			    // 1초 주기로 반복
			    Thread.sleep(1000);
			    j++;
		    }


		} catch(Exception e) {
			System.out.println("예외 발생");
		}
	}
	
	public interface User32 extends StdCallLibrary {
	    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
	    HWND GetForegroundWindow();  // add this
	    int GetWindowTextW(PointerType hWnd, char[] lpString, int nMaxCount);
	}
	
	
}
