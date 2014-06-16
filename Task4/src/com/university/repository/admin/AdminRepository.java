package com.university.repository.admin;

import com.university.domain.admin.Admin;

public interface AdminRepository {
    public Admin findAdmin(int id);
    public Admin findAdmin(String name);
}
