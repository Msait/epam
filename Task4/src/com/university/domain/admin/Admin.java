package com.university.domain.admin;

public class Admin {
    private Integer adminId;
    private String name;
    private String pass;
    
    public Integer getAdminId() {
        return adminId;
    }
    public void setAdminId(Integer id) {
        this.adminId = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    @Override
    public int hashCode() {
	int hash = 3;
        hash = 13 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 13 * hash + (this.pass != null ? this.pass.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Admin other = (Admin) obj;
        if ( (this.name == null) ? (other.name != null) : !this.name.equals(other.name) ) {
            return false;
        }
        if (this.pass != other.pass && (this.pass == null || !this.pass.equals(other.pass))) {
            return false;
        }
        return true;
    }
}
