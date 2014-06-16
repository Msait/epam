package com.university.service.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.university.domain.admin.Admin;
import com.university.repository.jdbc.DaoFactory;
import com.university.repository.jdbc.DaoFactory.ConnTypes;
import com.university.service.admin.AdminService;
import com.university.service.admin.AdminServiceImpl;

public class AuthServiceImpl implements AuthService {
    private final Logger logger = Logger.getLogger(AuthServiceImpl.class);

    @Override
    public boolean isAuthorized(HttpServletRequest request,
	    HttpServletResponse response) {
	HttpSession session = request.getSession(false);

	if (session != null && !session.isNew()
		&& session.getAttribute("admin") != null)
	    return true;

	return false;
    }

    /*
     * @Override public boolean isAuthorized(HttpServletRequest request,
     * HttpServletResponse response) {
     * 
     * boolean logged = Boolean.parseBoolean((String)
     * request.getAttribute("logged"));
     * 
     * logger.debug("Check logged flag:" + logged); if (logged) {
     * logger.debug("Session is valid"); return true; }
     * 
     * HttpSession session = request.getSession(); String name = null; String
     * pass = null;
     * 
     * DaoFactory daoFactory = DaoFactory.getDaoFactory(
     * ConnTypes.MySqlDaoFactory ); AdminService adminService = new
     * AdminServiceImpl( daoFactory.getAdminDao() );
     * 
     * // session exist if (session != null && !session.isNew() &&
     * session.getAttribute("adminName") != null) { name = (String)
     * session.getAttribute("adminName"); pass = (String)
     * session.getAttribute("adminPass"); pass = DigestUtils.md5Hex(pass);
     * 
     * if (userExistInDB(name, pass, adminService) ) return true; }
     * 
     * // check cookie Cookie[] cookie = request.getCookies();
     * 
     * for (Cookie c : cookie) { if (c.getName().equals("user")) { name =
     * c.getValue(); } else if (c.getName().equals("pass")) { pass =
     * c.getValue(); } }
     * 
     * if (name == null || pass == null) return false;
     * 
     * return userExistInDB(name, pass, adminService); }
     */

    @Override
    public boolean login(HttpServletRequest request,
	    HttpServletResponse response) {
	// read uname and password from the form
	String name = request.getParameter("uid");
	String pass = request.getParameter("upass");
	// make md5 hash
	pass = DigestUtils.md5Hex(pass);
	logger.debug("Generate password in md5: " + pass);

	Admin admin = new Admin();
	admin.setName(name);
	admin.setPass(pass);

	HttpSession session = request.getSession();

	DaoFactory daoFactory = DaoFactory
		.getDaoFactory(ConnTypes.MySqlDaoFactory);
	AdminService adminService = new AdminServiceImpl(
		daoFactory.getAdminDao());

	if (adminService.isExist(admin)) {
	    // setup session
	    logger.debug("Session is valid for: " + name);
	    session.setAttribute("admin", admin);

	    if (request.getParameter("saveCookie") != null) {
		logger.debug("Save cookie");
		saveCookie(name, pass, response);
	    }

	    return true;
	}
	logger.debug("Session is invalid");
	return false;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
	request.getSession().invalidate();

	Cookie[] cookies = request.getCookies();
	for (Cookie c : cookies) {
	    if (c.getName().equals("user")) {
		c.setMaxAge(0);
	    } else if (c.getName().equals("pass")) {
		c.setMaxAge(0);
	    }
	    response.addCookie(c);
	}
    }

    private void saveCookie(String name, String pass,
	    HttpServletResponse response) {
	Cookie user = new Cookie("user", name);
	Cookie password = new Cookie("pass", pass);
	// set cookie for 24h
	user.setMaxAge(60 * 60 * 24);
	password.setMaxAge(60 * 60 * 24);

	response.addCookie(user);
	response.addCookie(password);
    }

}
