package ru.patseev.qwiqqer.mailservice.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest implements Serializable {
	private String username;
	private String email;
}
