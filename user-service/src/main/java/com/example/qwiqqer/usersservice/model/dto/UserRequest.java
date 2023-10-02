package com.example.qwiqqer.usersservice.model.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {
	@NotBlank
	private String email;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
