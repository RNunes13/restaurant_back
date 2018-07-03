package br.com.nunes.restaurant.util;

public class CustomResponse implements ICustomResponse{
	private String message;
	private Boolean success;
	
	public CustomResponse(String message, Boolean success) {
		this.message = message;
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
