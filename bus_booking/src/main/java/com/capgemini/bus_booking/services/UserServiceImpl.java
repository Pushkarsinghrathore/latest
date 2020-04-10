package com.capgemini.bus_booking.services;

import java.util.List;

import com.capgemini.bus_booking.bean.Admin;
import com.capgemini.bus_booking.bean.Customer;
import com.capgemini.bus_booking.dao.AdminDaoImpl;
import com.capgemini.bus_booking.dao.CustomerDaoImpl;

public class UserServiceImpl implements UserServices {

	@Override
	public boolean loginCustomer(String userName, String password) {
		boolean check = false;
		Customer cst = new CustomerDaoImpl().findByUsername(userName);
		if (cst.getCust_pass().equals(password)) {
			check = true;
		}
		return check;
	}

	@Override
	public boolean loginAdmin(String userName, String password) {
		boolean check = false;
		Admin ad = new AdminDaoImpl().findByUsername(userName);
		if (ad.getAdmin_pass().equals(password)) {
			check = true;
		}
		return check;
	}

	@Override
	public List<Customer> forgotPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> addAdditionalData(String address, String phno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> changePassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void signupCustomer(List<Customer> lcst) {
		new CustomerDaoImpl().addCustomerDao(lcst);
	}

	@Override
	public void signupAdmin(List<Admin> ladmin) {
		new AdminDaoImpl().addAdminDao(ladmin);
	}

}
