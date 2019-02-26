package com.example.demo3;

import java.util.List;

import com.example.demo.response.Response;
import com.example.demo6.*;








public interface TeacherService {

	public Response addTeacher(TeacherModel teacher) throws Exception;
	
	public Response updateTeacher(TeacherModel teacherModel) throws Exception;

	public TeacherModel getTeacher(String teacherId) throws Exception;

	public boolean isTeacherNameExist(String userName) throws Exception;

	public Response deleteTeacher(String teacherId) throws Exception;

	public List<TeacherModel> getTeachers() throws Exception;
	
	public TeacherModel authenticate(TeacherModel teacher) throws Exception;

	public String forgotPassword(TeacherModel teacherModel) throws Exception;

	public boolean isteachernameExist(String teachername)  throws Exception;
	
	public Response updateTeacherStatus(TeacherModel teacherModel) throws Exception;

	
}

