package com.capgemini.bankappdbmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankappdbmvc.exceptions.CustomerNotFoundException;
import com.capgemini.bankappdbmvc.exceptions.LowBalanceException;
import com.capgemini.bankappdbmvc.repository.BankAccountRepository;
import com.capgemini.bankappdbmvc.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public double getBalance(long accountId) {
		try {
			return bankAccountRepository.getBalance(accountId);
		} catch (DataAccessException e) {
			CustomerNotFoundException notFoundException = new CustomerNotFoundException("User Not found");
			notFoundException.initCause(e);
			throw notFoundException;
		}
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException 
	{
			if (getBalance(accountId) - amount >= 0) {
				double newBalance = getBalance(accountId) - amount;
				bankAccountRepository.updateBalance(accountId, newBalance);
				return newBalance;
			}
			throw new LowBalanceException("Balance is low to make transaction");
	}

	@Override
	public double deposit(long accountId, double amount) {
		double newBalance = getBalance(accountId) + amount;
		if(bankAccountRepository.updateBalance(accountId, newBalance))
		return newBalance;
		return -1;
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException {
		double balance = withdraw(fromAccount, amount);
		if (balance != -1) {
			if (deposit(toAccount, amount) == -1) {
				deposit(fromAccount, amount);
				return false;
			}
			return true;
		}
		return false;
	}
}
