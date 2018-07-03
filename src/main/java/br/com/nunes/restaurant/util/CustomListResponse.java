package br.com.nunes.restaurant.util;

import java.util.List;

public class CustomListResponse implements ICustomResponse {
	private List<String> messages;
	private Boolean success;
	
	public CustomListResponse(List<String> messages, Boolean success) {
		this.messages = messages;
		this.success = success;
	}
	
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
