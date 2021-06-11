package com.oss.model;

public class Process {
	private String programName;		// 프로그램 명
	private String tabName;			// 탭 명
	private String startTime;		// 시작 시간
	
	public Process() {
		
	}
	
	public Process(String programName, String tabName, String startTime) {
		this.programName = programName;
		this.tabName = tabName;
		this.startTime = startTime;
	}
	
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public String getTabName() {
		return tabName;
	}
	
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
