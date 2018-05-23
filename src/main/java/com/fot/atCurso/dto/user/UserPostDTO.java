package com.fot.atCurso.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserPostDTO extends UserDTO {

	public String password;
}
