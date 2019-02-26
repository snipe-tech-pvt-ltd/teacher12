package com.example.demo1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="teacher11")
public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2585956794138390018L;

	@Id
	@Column(name="teacherId")
	private String teacherId;
	
    @Column(name="teachername")
	private String teachername;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="isActive")
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

	public   void setteachername(String teachername) {
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
	Teacher(String teacherId, String teachername, String email, String password,
			boolean isActive) 
	{
		super();
		this.teacherId = teacherId;
		this.teachername = teachername;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
	}
	public Teacher() 
	{
		super();
	}
	 public Teacher(String teacherId, String teachername, String email, String password) 
	 {
			super();
			this.teacherId = teacherId;
			this.teachername = teachername;
			this.email = email;
			this.password = password;
	 }
}
	

	

