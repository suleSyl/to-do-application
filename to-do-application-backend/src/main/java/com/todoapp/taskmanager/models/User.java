package com.todoapp.taskmanager.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@AllArgsConstructor
@Document
@ApiModel(value = "User Model Documentation", description = "Model")
public class User {
	@Id
	@ApiModelProperty(value = "unique id field of user object")
	private int id;
	@ApiModelProperty(value = "name field of user object")
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	@ApiModelProperty(value = "surname field of user object")
	@NotEmpty(message = "Surname cannot be empty")
	private String surname;
	@ApiModelProperty(value = "e-mail field of user object")
	@Email(message = "A valid e-mail address should be entered")
	private String email;
	@ApiModelProperty(value = "password field of user object")
	@Size(min = 4, message = "Password should have at least 4 characters")
	private String password;
}