package com.capgemini.bankappdbmvc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankappdbmvc.entities.BankAccount;
import com.capgemini.bankappdbmvc.entities.Customer;
import com.capgemini.bankappdbmvc.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Customer authenticate(Customer customer) throws DataAccessException {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM customers, bankAccounts where customers.accountId = bankAccounts.accountId and customerId = ? AND customerPassword = ?",
					new Object[] { customer.getCustomerId(), customer.getCustomerPassword() }, new CustomerRowMapper());
		} catch (DataAccessException e) {
			if (e instanceof EmptyResultDataAccessException) {
				e.initCause(new EmptyResultDataAccessException("Empty user not found", 1));
			}
			throw e;
		}
	}

//	public Customer getCustomer(Customer customer) {
//		try {
//			return jdbcTemplate.queryForObject(
//					"SELECT * FROM customers, bankAccounts where customers.accountId = bankAccounts.accountId and customerId = ?",
//					new Object[] { customer.getCustomerId() }, new CustomerRowMapper());
//		} catch (DataAccessException e) {
//			e.initCause(new EmptyResultDataAccessException("No customer found: Expected 1 Actual 0", 1));
//			throw e;
//		}
//	}

//	@Override
//	public Customer addCustomer(Customer customer) {
//		int count = jdbcTemplate.update("INSERT into customers VALUES(?,?,?,?,?,?)",
//				new Object[] { customer.getCustomerName(), customer.getCustomerPassword(), customer.getCustomerEmail(),
//						customer.getCustomerAddress(), customer.getCustomerDateOfBirth(), customer.getCustomerId() });
//
//		if (count != 0)
//			return customer;
//		return null;
//	}
//
//	@Override
//	public boolean deleteCustomer(long customerId) {
//		int count = jdbcTemplate.update("DELETE from customers WHERE customer_id=? ", new Object[] { customerId });
//		if (count != 0)
//			return true;
//		return false;
//	}

	@Override
	public Customer updateProfile(Customer customer) throws DataAccessException {
		try {
			jdbcTemplate.update(
					"update customers set customerName= ? ,customerPassword= ? ,customerEmail= ? ,customerAddress= ? , customerDateOfBirth= ? where customerId= ? ",
					new Object[] { customer.getCustomerName(), customer.getCustomerPassword(),
							customer.getCustomerEmail(), customer.getCustomerAddress(),
							customer.getCustomerDateOfBirth(), customer.getCustomerId() });
			return customer;
		} catch (DataAccessException e) {
			if (e instanceof EmptyResultDataAccessException) {
				e.initCause(new EmptyResultDataAccessException(1));
			}
			throw e;
		}
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword)
			throws DataAccessException {
		try {
			int count = jdbcTemplate.update(
					"UPDATE customers SET customerPassword=? WHERE customerId=? AND customerPassword=?",
					new Object[] { newPassword, customer.getCustomerId(), oldPassword });
			return count == 1 ? true : false;
		} catch (DataAccessException e) {
			if (e instanceof EmptyResultDataAccessException) {
				e.initCause(new EmptyResultDataAccessException("updation failed", 1));
			}
			throw e;
		}
	}

	private class CustomerRowMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

			BankAccount account = new BankAccount();
			account.setAccountId(rs.getInt(8));
			account.setAccountType(rs.getString(9));
			account.setAccountBalance(rs.getDouble(10));

			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt(1));
			customer.setCustomerName(rs.getString(2));
			customer.setCustomerPassword(rs.getString(3));
			customer.setCustomerEmail(rs.getString(4));
			customer.setCustomerAddress(rs.getString(5));
			customer.setCustomerDateOfBirth(rs.getDate(6).toLocalDate());
			customer.setCustomerAccount(account);

			return customer;
		}

	}
}