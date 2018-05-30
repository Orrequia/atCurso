package com.fot.atCurso.component.mapper.user;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fot.atCurso.dto.user.UserDTO;
import com.fot.atCurso.model.User;



@RunWith(MockitoJUnitRunner.class)
public class TestUserMapper {

	private static final String NAME = "Pepe";
	
	@InjectMocks
	UserMapper mapper = new UserMapperImpl();
	
	@Mock
	DozerBeanMapper dozer;
	
	@Test
	public void testDTOToModel() {
		final UserDTO userDTO = new UserDTO();
		userDTO.setName(NAME);
		final User user = new User();
		user.setName(NAME);
		Mockito.when(dozer.map(userDTO, User.class)).thenReturn(user);
		
		final User res = mapper.dtoToModel(userDTO);
		Assert.assertEquals(NAME, res.getName());
	}
}
