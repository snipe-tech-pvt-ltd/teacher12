package com.example.demo4;
import java.util.List;

import com.example.demo.response.Response;
import com.example.demo1.Teacher;
import com.example.demo6.TeacherModel;

public interface TeacherDAO {
	
	public Response addTeacher(TeacherModel teacher)throws Exception;
	
	public Response deleteTeacher(String teacherId)throws Exception;

	public Teacher getTeacher(String teacherId)throws Exception;

	public List<Teacher> getTeachers()throws Exception;

	public Teacher authenticate(Teacher teacher)throws Exception;

	public Response updateTeacher(TeacherModel teacher)throws Exception;

    public String forgotPassword(String teacherId, String encriptString)throws Exception;
	
    public Response updateTeacherStatus(Teacher teacher)throws Exception;

    public Teacher isTeacherExist(Teacher teacher)throws Exception;
  
    public boolean isTaecherNameExist(String teacherName) throws Exception;
	
    public boolean isteachernameExist(String teachername)throws Exception;

	boolean isTeacherNameExist(String teacherName) throws Exception;

	Response updateTeacher(Teacher teacher) throws Exception;

	

}

