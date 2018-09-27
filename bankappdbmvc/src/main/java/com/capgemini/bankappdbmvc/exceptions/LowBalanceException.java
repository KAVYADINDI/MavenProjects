package com.capgemini.bankappdbmvc.exceptions;

public class LowBalanceException extends Exception {
 public LowBalanceException(String message)
 {
	 super(message);
 }
}
