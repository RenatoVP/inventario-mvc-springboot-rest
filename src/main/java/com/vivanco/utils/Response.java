package com.vivanco.utils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime timeStamp = LocalDateTime.now();
	private int statusCode;
	private Map<String, String> data;
	
	public Response(int statusCode, Map<String, String> data) {
		this.statusCode = statusCode;
		this.data = data;
	}
	
}
