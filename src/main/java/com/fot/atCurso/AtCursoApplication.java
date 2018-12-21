package com.fot.atCurso;

import com.fot.atCurso.dao.PermissionDAO;
import com.fot.atCurso.dao.RoleDAO;
import com.fot.atCurso.dao.UserDAO;
import com.fot.atCurso.model.Permission;
import com.fot.atCurso.model.Role;
import com.fot.atCurso.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class AtCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtCursoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PermissionDAO privilegeDAO, RoleDAO roleDAO, UserDAO userDAO) {
		return args -> {

			Role r1 = new Role();
			r1.setName("ADMIN");
			List<Permission> apr1 = new ArrayList<>();

			Role r2 = new Role();
			r2.setName("SUPPORT");
			List<Permission> apr2 = new ArrayList<>();


			createPermission(privilegeDAO,"GET_COMPANY", apr1, apr2);
			createPermission(privilegeDAO,"POST_COMPANY", apr1, apr2);
			createPermission(privilegeDAO,"PUT_COMPANY", apr1, apr2);
			createPermission(privilegeDAO,"DELETE_COMPANY", apr1);

			createPermission(privilegeDAO,"GET_USER", apr1);
			createPermission(privilegeDAO,"POST_USER", apr1);
			createPermission(privilegeDAO,"PUT_USER", apr1);
			createPermission(privilegeDAO,"DELETE_USER", apr1);

			createPermission(privilegeDAO,"GET_STORE", apr1, apr2);
			createPermission(privilegeDAO,"POST_STORE", apr1, apr2);
			createPermission(privilegeDAO,"PUT_STORE", apr1, apr2);
			createPermission(privilegeDAO,"DELETE_STORE", apr1);

			createPermission(privilegeDAO,"GET_COMPANY_TYPE", apr1);
			createPermission(privilegeDAO,"POST_COMPANY_TYPE", apr1);
			createPermission(privilegeDAO,"PUT_COMPANY_TYPE", apr1);
			createPermission(privilegeDAO,"DELETE_COMPANY_TYPE", apr1);

			createPermission(privilegeDAO,"GET_CONTRACT", apr1);
			createPermission(privilegeDAO,"POST_CONTRACT", apr1);
			createPermission(privilegeDAO,"PUT_CONTRACT", apr1);
			createPermission(privilegeDAO,"DELETE_CONTRACT", apr1);

			createPermission(privilegeDAO,"GET_CONTRACT_LINE", apr1);
			createPermission(privilegeDAO,"POST_CONTRACT_LINE", apr1);
			createPermission(privilegeDAO,"PUT_CONTRACT_LINE", apr1);
			createPermission(privilegeDAO,"DELETE_CONTRACT_LINE", apr1);

			createPermission(privilegeDAO,"GET_CONTRACT_TYPE", apr1);
			createPermission(privilegeDAO,"POST_CONTRACT_TYPE", apr1);
			createPermission(privilegeDAO,"PUT_CONTRACT_TYPE", apr1);
			createPermission(privilegeDAO,"DELETE_CONTRACT_TYPE", apr1);

			createPermission(privilegeDAO,"GET_EMPLOYEE", apr1, apr2);
			createPermission(privilegeDAO,"POST_EMPLOYEE", apr1, apr2);
			createPermission(privilegeDAO,"PUT_EMPLOYEE", apr1, apr2);
			createPermission(privilegeDAO,"DELETE_EMPLOYEE", apr1);

			createPermission(privilegeDAO,"GET_DONGLE", apr1);
			createPermission(privilegeDAO,"POST_DONGLE", apr1);
			createPermission(privilegeDAO,"PUT_DONGLE", apr1);
			createPermission(privilegeDAO,"DELETE_DONGLE", apr1);

			createPermission(privilegeDAO,"GET_DONGLE_TYPE", apr1);
			createPermission(privilegeDAO,"POST_DONGLE_TYPE", apr1);
			createPermission(privilegeDAO,"PUT_DONGLE_TYPE", apr1);
			createPermission(privilegeDAO,"DELETE_DONGLE_TYPE", apr1);

			createPermission(privilegeDAO,"GET_POPULATION", apr1);
			createPermission(privilegeDAO,"POST_POPULATION", apr1);
			createPermission(privilegeDAO,"PUT_POPULATION", apr1);
			createPermission(privilegeDAO,"DELETE_POPULATION", apr1);

			createPermission(privilegeDAO,"GET_PRIVILEGE", apr1);
			createPermission(privilegeDAO,"POST_PRIVILEGE", apr1);
			createPermission(privilegeDAO,"PUT_PRIVILEGE", apr1);
			createPermission(privilegeDAO,"DELETE_PRIVILEGE", apr1);

			createPermission(privilegeDAO,"GET_PROVINCE", apr1);
			createPermission(privilegeDAO,"POST_PROVINCE", apr1);
			createPermission(privilegeDAO,"PUT_PROVINCE", apr1);
			createPermission(privilegeDAO,"DELETE_PROVINCE", apr1);

			createPermission(privilegeDAO,"GET_ROLE", apr1);
			createPermission(privilegeDAO,"POST_ROLE", apr1);
			createPermission(privilegeDAO,"PUT_ROLE", apr1);
			createPermission(privilegeDAO,"DELETE_ROLE", apr1);

			r1.setPermission(apr1);
			roleDAO.save(r1);

			r2.setPermission(apr2);
			roleDAO.save(r2);

			User orrequia = new User();
			User valeria = new User();

			orrequia.setName("Francisco");
			orrequia.setUsername("orrequia");
			orrequia.setEmail("orrequia8@gmail.com");
			orrequia.setPassword(DigestUtils.sha512Hex("1234"));
			orrequia.setRole(r1);
			userDAO.save(orrequia);

			valeria.setName("Valeria");
			valeria.setUsername("valeria");
			valeria.setEmail("orrequia8@gmail.com");
			valeria.setPassword(DigestUtils.sha512Hex("4321"));
			valeria.setRole(r2);
			userDAO.save(valeria);
		};

	}

	private void createPermission(PermissionDAO pDAO, String name, List<Permission> apr1) {
		Permission p1 = new Permission();
		p1.setName(name);
		pDAO.save(p1);
		apr1.add(p1);
	}

	private void createPermission(PermissionDAO pDAO, String name, List<Permission> apr1, List<Permission> apr2) {
		Permission p1 = new Permission();
		p1.setName(name);
		pDAO.save(p1);
		apr1.add(p1);
		apr2.add(p1);
	}
}
