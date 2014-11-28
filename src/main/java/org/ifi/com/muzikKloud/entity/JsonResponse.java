package org.ifi.com.muzikKloud.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResponse {

	@JsonProperty
	public String status = "";
	@JsonProperty
	public String errorMessage = "";

	public JsonResponse() {
		
	}
	
	public JsonResponse(String status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

}
