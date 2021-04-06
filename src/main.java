import java.util.Date;
import java.text.SimpleDateFormat;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public class main {
   public interface User32 extends StdCallLibrary {
      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
      HWND GetForegroundWindow();  // add this
      int GetWindowTextW(PointerType hWnd, char[] lpString, int nMaxCount);
   }

   public static void main(String[] args) throws InterruptedException {
      char[] windowText = new char[512];
      
      Date date_now = new Date(System.currentTimeMillis());
      SimpleDateFormat simple_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      
      //String filepath = "C:\\Users\\402-08\\Desktop\\jp\\Penguins.JP";
      //String[] ar = filepath.split("\\\\");
      //String file = ar[ar.length - 1];
      //System.out.println(file);
      
      PointerType hwnd = User32.INSTANCE.GetForegroundWindow(); // then you can call it!
      User32.INSTANCE.GetWindowTextW(hwnd, windowText, 512);
      System.out.println(Native.toString(windowText));
      
      // split을 통해 프로세스 정보에서 프로세스 이름만을 출력
      String str = Native.toString(windowText);
      String[] ar = str.split(" - ");
      String last = ar[ar.length-1];
      System.out.println(last);
      System.out.println(date_now);
      System.out.println(simple_format.format(date_now));
   }
}