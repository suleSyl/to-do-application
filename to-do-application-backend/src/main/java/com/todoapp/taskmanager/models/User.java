package com.todoapp.taskmanager.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.persistence.Entity;

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
	private String name;
	@ApiModelProperty(value = "surname field of user object")
	private String surname;
	@ApiModelProperty(value = "e-mail field of user object")
	private String email;
	@ApiModelProperty(value = "password field of user object")
	private String password;
}