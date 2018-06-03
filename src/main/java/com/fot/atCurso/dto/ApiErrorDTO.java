package com.fot.atCurso.dto;

import java.io.Serializable;

public class ApiErrorDTO implements Serializable {

	private static final long serialVersionUID = 980798687L;
	
	private Integer code;
	private String msg;
	
	public ApiErrorDTO(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public ApiErrorDTO() {
		super();
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) { 
		this.msg = msg;
	}
}
