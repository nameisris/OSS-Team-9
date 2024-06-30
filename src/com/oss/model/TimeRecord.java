package com.oss.model;

public class TimeRecord {
	private String programName;		// 프로그램명
	private Long usageTime;			// 사용 시간
	private String recordedDate;	// 사용 일자
	
	public TimeRecord() {
		
	}
	
	public TimeRecord(String programName, Long usageTime, String recordedDate) {
		this.programName = programName;
		this.usageTime = usageTime;
		this.recordedDate = recordedDate;
	}
	
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public Long getUsageTime() {
		return usageTime;
	}
	
	public void setUsageTime(Long usageTime) {
		this.usageTime = usageTime;
	}
	
	public String getRecordedDate() {
		return recordedDate;
	}
	
	public void setRecordedDate(String recordedDate) {
		this.recordedDate = recordedDate;
	}
}
