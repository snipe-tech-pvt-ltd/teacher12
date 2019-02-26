package com.example.demo3;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.constant.StatusCode;
import com.example.demo.response.Response;
import com.example.demo.utils.CommonUtils;
import com.example.demo.utils.SmtpMailSender;
import com.example.demo1.Teacher;
import com.example.demo4.TeacherDAO;
import com.example.demo5.TeacherMapper;
import com.example.demo6.TeacherModel;


@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	TeacherDAO teacherDAO;
	
	
	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	SmtpMailSender smtpMailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

	@Override
	
	public Response addTeacher(TeacherModel teacher) throws Exception
	
	{ Response response =new Response();
		try
		{
			Teacher teacher1	= new Teacher();
			teacher1.setTeacherId(CommonUtils.generateRandomId());
			teacher1.setteachername(teacher.getteachername());
		    teacher1.setemail(teacher.getemail());
		    teacher1.setpassword(CommonUtils.encriptString(teacher.getpassword()));
		    
		    
		    teacher1.setActive(true);
			
		    
		    
		    response = teacherDAO.addTeacher(teacher);
			return response;
		}
		catch(Exception e)
		{
			logger.info("Exception Service:" + e.getMessage());
			response.setStatus(StatusCode.ERROR.name());
			response.setMessage(e.getMessage());
			return response;
		}
	}

	@Override
	public Response updateTeacher(TeacherModel teacherModel) throws Exception {
		
		{
			Teacher teacher=new Teacher();
			BeanUtils.copyProperties(teacherModel, teacher);
			Response res=teacherDAO.updateTeacher(teacher);
			return res;
		}
	}
	/*--------------------------------------------get Teacher by ID-----------------------------------------*/
	
	
		@Override
		public  TeacherModel getTeacher(String teacherId) throws Exception {
			try 
			{
				TeacherModel teacherModel = new TeacherModel();
				Teacher teacher = teacherDAO.getTeacher(teacherId);
				BeanUtils.copyProperties(teacher, teacherModel);
				return teacherModel;
			} 
			catch(Exception e) 
			{
				logger.info("Exception getTeacher:", e);
				return null;
			}
		}

	
		
	@Override
	public boolean isTeacherNameExist(String userName) throws Exception {
		try {
			return teacherDAO.isTeacherNameExist(userName);
		} catch (Exception e) {
			logger.info("Exception isUserNameExist:", e);
			return false;
		}
	}

	

	@Override
	public Response deleteTeacher(String teacherId) throws Exception {
		try
		{
			return teacherDAO.deleteTeacher(teacherId);
		} 
		catch (Exception e) 
		{
			logger.info("Exception deleteTeacher:", e);
			return null;
	}
	}
	

	
	@Override
	public List<TeacherModel> getTeachers() throws Exception {
		try
		{
			List<Teacher> teachers = teacherDAO.getTeachers();
			return teacherMapper.entityList(teachers);
		} 
		catch (Exception ex)
		{
			logger.info("Exception getTeachers:", ex);
		}
		return null;
	}

	@Override
	public TeacherModel authenticate(TeacherModel teacherModel) throws Exception {
		teacherModel.setpassword(CommonUtils.encriptString(teacherModel.getpassword()));
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherModel, teacher);
		System.out.println(teacherModel.getemail());
		/*System.out.println(teacherModel.getphoneno());*/

		teacher = teacherDAO.authenticate(teacher);
		if (teacher == null)
			return null;
		BeanUtils.copyProperties(teacher, teacherModel);
		return teacherModel;
	}

@SuppressWarnings("unused")
	@Override
	public String forgotPassword(TeacherModel teacherModel) throws Exception {
		
		try {
			Teacher teacher = new Teacher();
			BeanUtils.copyProperties(teacherModel, teacher);
			teacher = teacherDAO.isTeacherExist(teacher);
			System.out.println(teacher.getteachername());
			if (teacher != null) {
				String password = CommonUtils.generateRandomId();
				//String password = "   ";
				
				String status = teacherDAO.forgotPassword(teacher.getTeacherId(), CommonUtils.encriptString(password));
				if (status.equals(StatusCode.SUCCESS.name())) {
					String email=teacher.getemail();
					String pass=password;
					smtpMailSender.send(email,"Snipe It tech pvt ltd reset password","forgot password ="+ pass);
				}
				return status;
			} else
				return StatusCode.ERROR.name(); 
		} catch (Exception e) {
			logger.error("Exception in forgotPassword:", e);
			return StatusCode.ERROR.name();
		}
	
}

@Override
public boolean isteachernameExist(String teachername) throws Exception {
 
	try {
		return teacherDAO.isteachernameExist(teachername);
	} catch (Exception e) {
		logger.info("Exception isteachernameExist:", e);
	return false;
}
}
@Override
public Response updateTeacherStatus(TeacherModel teacherModel) throws Exception {
	try {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherModel, teacher);
		Response response = teacherDAO.updateTeacherStatus(teacher);
		return response;
	} catch (Exception ex) {
		logger.info("Exception in updateTeacherStatus:" + ex.getMessage());
	}
	
	return null;
}




}





