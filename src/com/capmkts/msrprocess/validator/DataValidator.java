package com.capmkts.msrprocess.validator;

import java.util.ArrayList;
import java.util.List;

public class DataValidator {

	private boolean isValid;
	private List<String> messageList = new ArrayList<String>();

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}

	public void addMessage(String message) {
		messageList.add(message);
	}

}
