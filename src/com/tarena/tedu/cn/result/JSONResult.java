package com.tarena.tedu.cn.result;

public class JSONResult<T> {
	private int status;
	//��ʾ��Ϣ
	private String msg;
	//��������
	private T result;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
}
