package cn.sits.rjb.common.data;

import java.io.Serializable;

/**
 * Create by Bizfocus China
 * 
 * @Author:Administrator
 * @Date:2017年12月8日
 * @Time:下午4:24:51
 * @version:1.0
 */
public class ResponseData<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T data;
	private String result;
	private String message;

	/*
	 * 封装response
	 */
	public ResponseData(T data, String result, String message) {
		this.data = data;
		this.result = result;
		this.message = message;
	}
	/*
	 * 封装response
	 */
	public ResponseData(String result, String message) {
		this.result = result;
		this.message = message;
	}
	/*
 	* 封装response
 	*/
	public ResponseData(String message) {
		new ResponseData("0",message);
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
