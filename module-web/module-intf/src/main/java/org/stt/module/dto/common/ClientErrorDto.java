package org.stt.module.dto.common;

import java.util.HashMap;
import java.util.Map;


/** 
* @author  jiying 
* @date 2015年9月16日
* @version 1.0  
*/
public class ClientErrorDto {
	
	private Map<String, String> fieldErrorMessage = new HashMap<String, String>();
	
	private String crossFieldErrorMessage;
	
	public Map<String, String> getFieldErrorMessage() {
		return fieldErrorMessage;
	}

	public void addFieldErrorMessage(String key, String value){
		this.fieldErrorMessage.put(key, value);
	}

	public String getCrossFieldErrorMessage() {
		return crossFieldErrorMessage;
	}

	public void setCrossFieldErrorMessage(String crossFieldErrorMessage) {
		this.crossFieldErrorMessage = crossFieldErrorMessage;
	}
	

}
