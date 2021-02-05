package com.github.camque.mutants.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"message",
	"response"
})
public class MutantResponse implements Serializable {

	private static final long serialVersionUID = 2237899964629400669L;

	@JsonProperty("message")
	private String message;

	@JsonProperty("response")
	private boolean response;

	public MutantResponse(String message, boolean response) {
		super();
		this.message = message;
		this.response = response;
	}

	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isResponse() {
		return this.response;
	}
	public void setResponse(boolean response) {
		this.response = response;
	}

}
