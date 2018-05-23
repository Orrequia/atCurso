package com.fot.atCurso.component.mapper.user;

import org.springframework.stereotype.Component;

import com.fot.atCurso.component.mapper.AbstractMapper;
import com.fot.atCurso.dto.user.UserDTO;
import com.fot.atCurso.model.User;

@Component
public class UserMapperImpl extends AbstractMapper<User, UserDTO> implements UserMapper{

	@Override
	public Class<? extends UserDTO> dtoClazz() {
		return UserDTO.class;
	}

	@Override
	public Class<? extends User> modelClazz() {
		return User.class;
	}

}
