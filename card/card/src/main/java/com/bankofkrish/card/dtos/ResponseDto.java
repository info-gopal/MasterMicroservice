package com.bankofkrish.card.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Response",description = "Returns of Response code and message")
public class ResponseDto {

	String statusCode;
	String statusMessage;
	

	public ResponseDto(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return "ResponseDto [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
	}

}
