package com.example.demo6;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class TeacherModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4587430320826983700L;

	private String teacherId;

	private String teachername;
	
    private String email;
    
    
	private String password;
	
    private boolean isActive;
	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getteachername() {
		return teachername;
	}

	public void setteachername(String teachername) {
		this.teachername = teachername;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public TeacherModel() {

	}

    
    public TeacherModel(String teacherId, String teachername, String email,  String password,
			boolean isActive) {
		this.teacherId = teacherId;
		this.teachername = teachername;
		this.email = email;
		
		this.password = password;
		this.isActive = isActive;
	}

	
	
    
}

