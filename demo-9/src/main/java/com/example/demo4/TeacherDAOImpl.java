package com.example.demo4;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.example.constant.StatusCode;
import com.example.demo1.Teacher;
import com.example.demo6.TeacherModel;

//import com.example.demo.model.parent.UpdatePassword;
//import com.example.demo.model.parent.ParentModel;
import com.example.demo.response.Response;
import com.example.demo.utils.CommonUtils;


@Repository
@Transactional
public  class TeacherDAOImpl implements TeacherDAO {
	
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherDAOImpl.class);


	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Response addTeacher(TeacherModel teacher) throws Exception {
		Response response = CommonUtils.getResponseObject("Add Teacher data");
		try 
		{
			entityManager.persist(teacher);
			response.setStatus(StatusCode.SUCCESS.name());
		} 
		catch (Exception e) 
		{
			logger.error("Exception in addUser", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return response;

}
	
	@Override
	public Response deleteTeacher(String teacherId) throws Exception {
		Response response = CommonUtils.getResponseObject("Delete Teacher data");
		try {
			

			Teacher teacher=getTeacher(teacherId);
			teacher.setActive(false);


		
			entityManager.flush();

			response.setStatus(StatusCode.SUCCESS.name());
			
		} 
		catch(Exception ex)
		{
			logger.error("Exception in deleteTeacher", ex);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(ex.getMessage());
		}
		return response;	
	}

	
	
@Override
	public boolean isTeacherNameExist(String teacherName) throws Exception {
		try {
			String hql = "FROM User WHERE teachername=?1 and isActive=true";
			int count = entityManager.createQuery(hql).setParameter(0, teacherName).getResultList().size();
			System.out.println(count);
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isteacherNameExist: ", e);
		}
		return false;
	}
	
	
	
	@Override
	public Teacher getTeacher(String teacherId) throws Exception {

		try 
		{
			String hql = "From Teacher where teacherId=?1 and isActive=true";
			return (Teacher) entityManager.createQuery(hql).setParameter(0, teacherId).getSingleResult();
		} 
		catch (EmptyResultDataAccessException e) 
		{
			return null;
		} 
		catch (Exception e) 
		{
			logger.error("Exception in getTeacher"+ e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> getTeachers() throws Exception {
		try 
		{
			String hql = "FROM Teacher where isActive=true";
			
			return (List<Teacher>) entityManager.createQuery(hql).getResultList();
		} 
		catch (Exception e)
		{
			logger.error("Exception in getTeachers", e);
		}
		return null;
	}

	@Override
	public Teacher authenticate(Teacher teacher) throws Exception {
		try 
		{
		String hql = "FROM Taecher where email=:email  and password=:password  and isActive=true";
		return (Teacher) entityManager.createQuery(hql).setParameter("email", teacher.getemail()).setParameter("password", teacher.getpassword()).getSingleResult();
		} 
		catch (Exception e)
		{
		logger.error("Exception in auteneticate", e);
	}
		return null;
	}

	@Override
	public Response updateTeacher(Teacher teacher) throws Exception {
		Response response = CommonUtils.getResponseObject("Update Teacher data");
		try 
		{	
			Teacher teachers = getTeacher(teacher.getTeacherId());
			teachers.setTeacherId(teacher.getTeacherId());
			teachers.setteachername(teacher.getteachername());
			teachers.setemail(teacher.getemail());
			teachers.setActive(true);
			teachers.setpassword(CommonUtils.encriptString(teacher.getpassword()));
	        entityManager.flush();
			response.setStatus(StatusCode.SUCCESS.name());
		} 
		catch 	(Exception e) 
		{
			logger.error("Exception in updateTeacher", e);
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors(e.getMessage());
		}
		return  response;
	}

	@Override
	public String forgotPassword(String teacherId, String encriptString) throws Exception {
		try {
			Teacher fran = getTeacher(teacherId);
			fran.setpassword(encriptString);
			entityManager.flush();
			return StatusCode.SUCCESS.name();
	} catch (Exception e) {
		logger.error("Exception in forgotPassword", e);
		return StatusCode.ERROR.name();
	}
	}

	@Override
	public boolean isteachernameExist(String teachername) throws Exception {
		try {
			String hql = "FROM Parent WHERE teachername=?1 and  isActive=true ";
			int count = entityManager.createQuery(hql).setParameter(1, teachername).getResultList().size();
			System.out.println(count);
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.error("Exception in isteachernameExist: ", e);
		}
		return false;
	}
	//@Override
	//public Response updateTeacherStatus(Teacher teacher) throws Exception {
		//Response response = CommonUtils.getResponseObject("Update Teacher data");
		//try {
			//Teacher teacher1 = getTeacher(teacher.getteacherId());
			
			
			//entityManager.flush();
			//response.setStatus(StatusCode.SUCCESS.name());
		//} catch (Exception e) {
			//logger.error("Exception in deleteTeacher", e);
			//response.setStatus(StatusCode.ERROR.name());
			//response.setErrors(e.getMessage());
		//}
		//return response;
	//}

	@Override
	public Teacher isTeacherExist(Teacher teacher) throws Exception {
		try {
			String hql = "FROM Teacher where email=?1 and isActive=true";
			return (Teacher) entityManager.createQuery(hql).setParameter(1, teacher.getemail()).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in isTeacherExist", e);
		}
	
	
		return teacher;
	}

	@Override
	public boolean isTaecherNameExist(String teacherName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Response updateTeacherStatus(Teacher teacher) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateTeacher(TeacherModel teacher) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
