package com.yuhang.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserAccountResource {

	UserAccountRepository uaRepo = new UserAccountRepository();
	
	
	@RequestMapping("userAccounts")
	public List<UserAccount> getUserAccounts() {

		return uaRepo.getUserAccounts();
	}
	
	
	@RequestMapping("userAccount/{uid}")	
	public UserAccount getUserAccount(@PathVariable("uid") int id) {
		
		UserAccount ua = uaRepo.getUserAccount(id);		
		return ua;
	}
	
	
	
	@PostMapping("userAccount")
	public void createUserAccount(@RequestBody UserAccount ua) {
		
		uaRepo.addUserAccount(ua);
	}


	@PutMapping("userAccount")
	public void updateUserAccount(@RequestBody UserAccount ua) {
		
		if(uaRepo.getUserAccount(ua.getId()).getId()==0)
		{uaRepo.addUserAccount(ua);}
		else
		{uaRepo.updateUserAccount(ua);}
		
	}

	
	@DeleteMapping("userAccount/{uid}")	
	public void delUserAccount(@PathVariable("uid") int id) {
		
		if(uaRepo.getUserAccount(id).getId()!=0)
			uaRepo.deleteUserAccount(id);
		
	}
}
