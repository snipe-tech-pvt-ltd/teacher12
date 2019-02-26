package com.example.demo5;

import org.springframework.stereotype.Component;

import com.example.demo1.Teacher;
import com.example.demo.mapper.AbstractModelMapper;
import com.example.demo6.TeacherModel;


@Component
public class TeacherMapper extends AbstractModelMapper<TeacherModel, Teacher>{

	@Override
	public Class<TeacherModel> entityType() {
		return TeacherModel.class;
	}

	@Override
	public Class<Teacher> modelType() {
		return Teacher.class;
	}


}
