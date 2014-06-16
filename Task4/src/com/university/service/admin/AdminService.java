package com.university.service.admin;

import com.university.domain.admin.Admin;

public interface AdminService {
    public Admin findAdmin(int id);
    public Admin findAdmin(String name);
    public boolean isExist(String name, String pass);
    public boolean isExist(Admin admin);
}
