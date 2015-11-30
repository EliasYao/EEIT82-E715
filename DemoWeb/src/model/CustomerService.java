package model;

import java.util.Arrays;

import model.dao.CustomerDAOJdbc;

public class CustomerService {
	private CustomerDAO customerDao = new CustomerDAOJdbc();
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		if(bean!=null) {
			if(password!=null && password.length()!=0) {
				byte[] pass = bean.getPassword();	//資料庫抓出的密碼
				byte[] temp = password.getBytes();	//使用者輸入的密碼
				if(Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if(bean!=null) {
			if(newPassword!=null && newPassword.length()!=0) {
				byte[] temp = newPassword.getBytes();	//使用者輸入的密碼
				return customerDao.update(
						temp, bean.getEmail(), bean.getBirth(), username);
			}
		}
		return false;
	}
	public static void main(String[] args) {
		CustomerService service = new CustomerService();
		CustomerBean select = service.login("Alex", "A");
		System.out.println(select);
		System.out.println(service.changePassword("Alex", "AAA", "A"));
	}
}
