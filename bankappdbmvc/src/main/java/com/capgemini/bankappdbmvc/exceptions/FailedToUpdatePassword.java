package com.capgemini.bankappdbmvc.exceptions;

public class FailedToUpdatePassword  extends RuntimeException{

	public FailedToUpdatePassword(String message) {
		super(message);
	}

}
