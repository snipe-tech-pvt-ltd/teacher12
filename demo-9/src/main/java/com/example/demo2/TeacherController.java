package com.example.demo2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.constant.StatusCode;
import com.example.demo4.TeacherDAO;
import com.example.demo6.UpdatePassword;
import com.example.demo6.TeacherModel;

import com.example.demo.response.ErrorObject;
import com.example.demo.response.Response;
import com.example.demo3.TeacherService;
import com.example.demo.utils.CommonUtils;
import com.example.demo1.Teacher;



@RestController
@EnableAutoConfiguration
@RequestMapping("/413")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")

public class TeacherController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired 
	TeacherService teacherService;
	
	@Autowired 
	TeacherDAO teacherDAO;
	
	
	/*--------------------------------------------Add teacher-----------------------------------------*/
	
	
	@RequestMapping(value = "/teacher", method = RequestMethod.POST, produces = "application/json")
	public Response addTeacher(@RequestBody TeacherModel teacher, HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		logger.info("addTeacher: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addUser: Received request: "+ CommonUtils.getJson(teacher));
		
		return teacherService.addTeacher(teacher);
		
	}
	
	
	/*--------------------------------------------get Teacher by ID-----------------------------------------*/
	
	
	@RequestMapping(value = "/getTeacher/{teacherId}", method = RequestMethod.GET, produces = "application/json")
public @ResponseBody String getTeacher(@PathVariable("teacherId") String teacherId, HttpServletRequest request,
		HttpServletResponse response) throws Exception 
{

	logger.info("getTeacher: Received request: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	
	TeacherModel teacherModel = teacherService.getTeacher(teacherId);
	
	 Response res = CommonUtils.getResponseObject("Teacher Details");
	if (teacherModel == null)
	{
		ErrorObject err = CommonUtils.getErrorResponse("teachers Not Found", "Teachers Not Found");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
	} 
	else
	{
		res.setData(teacherModel);
	}
	logger.info("getParent: Sent response");
	return CommonUtils.getJson(res);
}


/*--------------------------------------------update teacher -----------------------------------------*/

@RequestMapping(value = "/updateTeacher", method = RequestMethod.PUT, produces = "application/json")
public Response updateTeacher(@RequestBody TeacherModel teacherModel, HttpServletRequest request, HttpServletResponse response)
		throws Exception 
{
	logger.info("updateteacher: Received request URL: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	logger.info("updatetaecher: Received request: " + CommonUtils.getJson(teacherModel));
	
	return teacherService.updateTeacher(teacherModel);
	
}
/*--------------------------------------------get Teachers -----------------------------------------*/
@RequestMapping(value = "/Teachers", method = RequestMethod.GET, produces = "application/json")
public @ResponseBody String getTaechers(HttpServletRequest request, HttpServletResponse response) throws Exception 
{
	logger.info("getTaechers: Received request: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	List<TeacherModel> model = teacherService.getTeachers();
	
	
	Response res = CommonUtils.getResponseObject("List of teachers");
	if (model == null) {
		ErrorObject err = CommonUtils.getErrorResponse("Teachers Not Found", "Teachers Not Found");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
	} else {
		res.setData(model);
	}
	logger.info("getTeachers: Sent response");
	return CommonUtils.getJson(res);
}

/*--------------------------------------------delete Teachers -----------------------------------------*/
@RequestMapping(value="/deleteTaecher/{TeacherId}",method = RequestMethod.DELETE, produces = "application/json")
public @ResponseBody Response deleteTaecher (@PathVariable("teacherId") String teacherId, HttpServletRequest request, HttpServletResponse response) throws Exception
{
	logger.info("getTeacher: Received request:" +request.getRequestURL().toString()
			+((request.getQueryString()==null)?  "" : "?" +request.getQueryString().toString()));
	
	return teacherService.deleteTeacher(teacherId);
}
/*--------------------------------------------teacher login -----------------------------------------*/
@RequestMapping(value="/login",method = RequestMethod.POST, produces ="application/json")
public @ResponseBody String authenticate (@RequestBody TeacherModel teacher, HttpServletRequest request, HttpServletResponse response) throws Exception

{
	logger.info("authenticate: Received request: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	logger.info("authenticate :Received request: " + CommonUtils.getJson(teacher));
	
	teacher =teacherService.authenticate(teacher);
	
	Response res=CommonUtils.getResponseObject("authenticate Teacher");
	if(teacher==null)
	{
		ErrorObject err=CommonUtils.getErrorResponse("Invalid Fullname or Password", "Invalid Fullname or Password");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
	}
	{
		res.setData(teacher);
		
	}
	logger.info("authenticate:sent response");
	return CommonUtils.getJson(res);
}
/*--------------------------------------------Forgot Password -----------------------------------------*/
@RequestMapping(value="/forgotPassword",method = RequestMethod.PUT, produces ="application/json")
public @ResponseBody String resetPassword(@RequestBody TeacherModel teacherModel, HttpServletRequest request,
		HttpServletResponse response) throws Exception
{
	logger.info("forgotPassword: Received request URL: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	logger.info("forgotPassword: Received request: "+CommonUtils.getJson(teacherModel));
	
	String status = teacherService.forgotPassword(teacherModel);
	Response res = CommonUtils.getResponseObject("forgot password");
	if (status.equalsIgnoreCase(StatusCode.ERROR.name())) {
		ErrorObject err = CommonUtils.getErrorResponse("forgot password failed", "forgot password failed");
		res.setErrors(err);
		res.setStatus(StatusCode.ERROR.name());
}
	logger.info("forgotPassword: Sent response");
	return CommonUtils.getJson(res);
}
/*--------------------------------------------Teacher status -----------------------------------------*/

@RequestMapping(value = "/TeacherStatus", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
public Response updateTeacherStatus(@RequestBody TeacherModel teacherModel, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	logger.info("updateTeacherStatus: Received request URL: " + request.getRequestURL().toString()
			+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
	logger.info("updateTeacherStatus: Received request: " + CommonUtils.getJson(teacherModel));
	return teacherService.updateTeacherStatus(teacherModel);

}
}

