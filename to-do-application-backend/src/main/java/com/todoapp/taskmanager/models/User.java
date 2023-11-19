package com.todoapp.taskmanager.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@ApiModel(value = "User Model Documentation", description = "Model")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	@ApiModelProperty(value = "unique id field of user object")
	private String id;
	@ApiModelProperty(value = "username field of user object")
	@NotEmpty(message = "Username cannot be empty")
	private String username;
	@ApiModelProperty(value = "password field of user object")
	@Size(min = 4, message = "Password should have at least 4 characters")
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}