package com.oss.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	public void getExcel(String path, List<String> list) {
		
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		Row row = null;
		Cell cell = null;
		
		//.xlsx 확장자 지원
		XSSFWorkbook xssfWb = null; // .xlsx
		XSSFSheet xssfSheet = null; // .xlsx
		XSSFRow xssfRow = null; // .xlsx
		XSSFCell xssfCell = null;// .xlsx
			
			try {
				int rowNo = 0; // 행 갯수 
				// 워크북 생성
				xssfWb = new XSSFWorkbook();
				xssfSheet = xssfWb.createSheet("엑셀 테스트"); // 워크시트 이름
				
				//헤더용 폰트 스타일
				XSSFFont font = xssfWb.createFont();
				font.setFontName(HSSFFont.FONT_ARIAL); //폰트스타일
				font.setFontHeightInPoints((short)14); //폰트크기
				font.setBold(true); //Bold 유무
				
				//테이블 타이틀 스타일
				CellStyle cellStyle_Title = xssfWb.createCellStyle();
				
				xssfSheet.setColumnWidth(3, (xssfSheet.getColumnWidth(3))+(short)2048); // 3번째 컬럼 넓이 조절
				xssfSheet.setColumnWidth(4, (xssfSheet.getColumnWidth(4))+(short)2048); // 4번째 컬럼 넓이 조절
				xssfSheet.setColumnWidth(5, (xssfSheet.getColumnWidth(5))+(short)2048); // 5번째 컬럼 넓이 조절
				
				xssfSheet.setColumnWidth(8, (xssfSheet.getColumnWidth(8))+(short)4096); // 8번째 컬럼 넓이 조절
				
				cellStyle_Title.setFont(font); // cellStle에 font를 적용
				cellStyle_Title.setAlignment(HorizontalAlignment.CENTER); // 정렬
				
				//셀병합
				xssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8)); //첫행, 마지막행, 첫열, 마지막열( 0번째 행의 0~8번째 컬럼을 병합한다)
				//타이틀 생성
				xssfRow = xssfSheet.createRow(rowNo++); //행 객체 추가
				xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
				xssfCell.setCellStyle(cellStyle_Title); // 셀에 스타일 지정
				xssfCell.setCellValue("Program Log Excel"); // 데이터 입력
				
				xssfRow = xssfSheet.createRow(rowNo++);  // 빈행 추가
				
				CellStyle cellStyle_Body = xssfWb.createCellStyle(); 
				cellStyle_Body.setAlignment(HorizontalAlignment.LEFT); 
				
				//헤더 생성
				
				CellStyle cellStyle_Table_Center = xssfWb.createCellStyle();
				cellStyle_Table_Center.setBorderTop(BorderStyle.THIN); //테두리 위쪽
				cellStyle_Table_Center.setBorderBottom(BorderStyle.THIN); //테두리 아래쪽
				cellStyle_Table_Center.setBorderLeft(BorderStyle.THIN); //테두리 왼쪽
				cellStyle_Table_Center.setBorderRight(BorderStyle.THIN); //테두리 오른쪽
				cellStyle_Table_Center.setAlignment(HorizontalAlignment.CENTER);
				
				for (int i = 0; i < list.size(); i ++) {
					//rowNo++ = 행값
					xssfRow = xssfSheet.createRow(rowNo++);
					xssfCell = xssfRow.createCell((short) 0);
					xssfCell.setCellStyle(cellStyle_Table_Center);
					xssfCell.setCellValue(list.get(i)); // 저장하고싶은 값 세팅

				}
				
				
				String localFile = "C:\\ExcelExport\\" + "test_e123xcel" + ".xlsx";
				//저장하려고하는 경로
				
				File file = new File(localFile);
				FileOutputStream fos = null;
				fos = new FileOutputStream(file, true);
				xssfWb.write(fos);
				
				if (xssfWb != null)	xssfWb.close();
				if (fos != null) fos.close();

			}
			catch(Exception e){
	        	
			}
	}
	
}
