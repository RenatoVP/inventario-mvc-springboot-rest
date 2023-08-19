package com.vivanco.utils;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageResponse implements Serializable {
private static final long serialVersionUID = 1L;
	
	private LocalDateTime timeStamp = LocalDateTime.now();
	private String message;
	
	public MessageResponse(String message) {
		this.message = message;
	}
	
}
