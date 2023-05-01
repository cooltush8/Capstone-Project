package com.hdfc.midterm.leavemanagemetsystem.entity;

public class LeaveApprovalRequest {
private LeaveStatus action;

private String comments;

public boolean isValidAction() {
  return action == LeaveStatus.APPROVED || action == LeaveStatus.REJECTED;
}

public LeaveApprovalRequest(LeaveStatus action, String comments) {
	super();
	this.action = action;
	this.comments = comments;
}

public LeaveStatus getAction() {
	return action;
}

public void setAction(LeaveStatus action) {
	this.action = action;
}

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public LeaveApprovalRequest() {
	super();
}
	  

}
