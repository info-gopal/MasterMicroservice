package com.bankofkrish.card.dtos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Returns ErrorResponse",name = "ErrorResponse")
@Data
public class ErrorResponseDto {
@Schema(description = "API path invoked by client")
	private String apiPath;
@Schema(description = "Error code representing the error happend")
	private HttpStatus errorCode;	
@Schema(description = "Error Message representing the error happend")
	private String errorMessage;
@Schema(description = "Error Accured time")
	private LocalDateTime errorTime;

	/**
	 * @param apiPath
	 * @param errorCode
	 * @param errorMessage
	 * @param errorTime
	 */

	


	public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
		super();
		this.apiPath = apiPath;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}


	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorResponseDto [apiPath=" + apiPath + ", errorCode=" + errorCode + ", errorTime=" + errorTime
				+ ", errorMessage=" + errorMessage + "]";
	}

}
