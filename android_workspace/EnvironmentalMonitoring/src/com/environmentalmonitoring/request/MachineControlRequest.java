package com.environmentalmonitoring.request;

import org.json.JSONException;
import org.json.JSONObject;

import com.environmentalmonitoring.config.Config;

public class MachineControlRequest extends BaseRequest {
	private int which = -1;
	private boolean onoff = false;

	public void setControl(int which, boolean onoff) {
		this.which = which;
		this.onoff = onoff;
	}

	@Override
	public byte[] getSend_byte() {
		// TODO Auto-generated method stub
		switch (which) {
		case 0:
			if (onoff) {
				return Config.MACHINE00_OPEN;
			}
			return Config.MACHINE00_CLOSE;
		case 1:
			if (onoff) {
				return Config.MACHINE01_OPEN;
			}
			return Config.MACHINE01_CLOSE;
		case 2:
			if (onoff) {
				return Config.MACHINE02_OPEN;
			}
			return Config.MACHINE02_CLOSE;
		case 3:
			if (onoff) {
				return Config.MACHINE03_OPEN;
			}
			return Config.MACHINE03_CLOSE;
		}
		return null;
	}

	@Override
	public String getSend() {
		// TODO Auto-generated method stub
		String value = "";
		switch (which){
		case 0:
			value =  onoff?"switch01":"switch00";
			break;
		case 1:
			value =  onoff?"switch11":"switch10";
			break;
		case 2:
			value =  onoff?"switch21":"switch20";
			break;
		case 3:
			value =  onoff?"switch31":"switch30";
			break;
		}
		JSONObject json = new JSONObject();
		try {
			json.put("flag", value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	@Override
	public void parseBack(byte[] back) {
		// TODO Auto-generated method stub
		if (true) {
			onSocketOverListener.onBackSuccess(this);
		} else {
			onSocketOverListener.onBackFailed(this);
		}
	}

}
