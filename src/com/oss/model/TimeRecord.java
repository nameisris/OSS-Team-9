package com.oss.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TimeRecord {
	private String programName;
	private Long usageTime;
	private String recordedDate;
	
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
