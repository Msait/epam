package com.university.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.university.domain.faculty.Faculty;

public class ShowFaculties extends TagSupport {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Faculty val;

    @Override
    public int doStartTag() throws JspException {
	JspWriter out = pageContext.getOut();
	
	try {
	    out.println( "<a href='viewDepartmentSubjects?id=" + val.getFacultyId() + "'>" );
	    out.println(val.getFacultyName() + "</a>");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
        return SKIP_BODY;
    }
    
    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    public void setVal(Faculty val) {
        this.val = val;
    }

}
