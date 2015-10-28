package com.environmentalmonitoring.request;

public class BaseRequest {
	
	
	
	
	
	public interface OnSocketOverListener{
		void onBackSuccess(BaseRequest baserequest);
		
		void onBackFailed(BaseRequest baserequest);
	}

	protected OnSocketOverListener onSocketOverListener;

	public void setOnSocketOverListener(OnSocketOverListener onSocketOverListener) {
		this.onSocketOverListener = onSocketOverListener;
	}
	
	public String getSend(){
		return null;
	}
	
	public byte[] getSend_byte(){
		return null;
	}
	
	public void parseBack(String back){}
	
	public void parseBack(byte[] back){}
}
