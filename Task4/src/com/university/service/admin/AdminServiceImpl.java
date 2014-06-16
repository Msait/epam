package com.university.service.admin;

import com.university.domain.admin.Admin;
import com.university.repository.admin.AdminRepository;

public class AdminServiceImpl implements AdminService {
    
    private AdminRepository repository;
    
    public AdminServiceImpl(AdminRepository adminRepository) {
	repository = adminRepository;
    }

    @Override
    public Admin findAdmin(int id) {
	return repository.findAdmin(id);
    }

    @Override
    public Admin findAdmin(String name) {
	return repository.findAdmin(name);
    }

    @Override
    public boolean isExist(String name, String pass) {
	Admin admin = repository.findAdmin(name);
	if (admin ==  null || admin.getName() == null || admin.getPass() == null) {
	    return false;
	}
	
	if ( !admin.getName().equalsIgnoreCase(name) ) {
	    return false;
	}
	
	if (admin.getPass().equals( pass ) )
	    return true;
	
	return false;
    }

    @Override
    public boolean isExist(Admin admin) {
	return isExist(admin.getName(), admin.getPass());
    }
    
    

}
