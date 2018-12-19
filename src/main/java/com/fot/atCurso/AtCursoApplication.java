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

import java.util.ArrayList;
import java.util.List;

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


			Permission p1 = new Permission();
			p1.setName("GET_COMPANY");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("POST_COMPANY");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("PUT_COMPANY");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_COMPANY");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_USER");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_STORE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("POST_STORE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("PUT_STORE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_STORE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_COMPANY_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_COMPANY_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_COMPANY_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_COMPANY_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_CONTRACT");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_CONTRACT");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_CONTRACT");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_CONTRACT");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_CONTRACT_LINE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_CONTRACT_LINE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_CONTRACT_LINE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_CONTRACT_LINE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_CONTRACT_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_CONTRACT_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_CONTRACT_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_CONTRACT_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_EMPLOYEE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("POST_EMPLOYEE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("PUT_EMPLOYEE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			apr2.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_EMPLOYEE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_DONGLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_DONGLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_DONGLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_DONGLE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_DONGLE_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_DONGLE_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_DONGLE_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_DONGLE_TYPE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_POPULATION");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_POPULATION");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_POPULATION");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_POPULATION");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_PRIVILEGE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_PROVINCE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_PROVINCE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_PROVINCE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_PROVINCE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			p1 = new Permission();
			p1.setName("GET_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("POST_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("PUT_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);
			p1 = new Permission();
			p1.setName("DELETE_ROLE");
			privilegeDAO.save(p1);
			apr1.add(p1);

			r1.setPermission(apr1);
			roleDAO.save(r1);

			r2.setPermission(apr2);
			roleDAO.save(r2);

			User orrequia = new User();
			User valeria = new User();

			orrequia.setName("Francisco");
			orrequia.setUsername("orrequia");
			orrequia.setPassword(DigestUtils.sha512Hex("1234"));
			orrequia.setRole(r1);
			userDAO.save(orrequia);

			valeria.setName("Valeria");
			valeria.setUsername("valeria");
			valeria.setPassword(DigestUtils.sha512Hex("4321"));
			valeria.setRole(r2);
			userDAO.save(valeria);
		};
	}
}
