package com.university.service.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    boolean isAuthorized(HttpServletRequest request, HttpServletResponse response);
    boolean login(HttpServletRequest request, HttpServletResponse response);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
