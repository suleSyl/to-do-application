package com.todoapp.taskmanager.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@Document
@ApiModel(value = "Task Model Documentation", description = "Model")
public class Task {
	@Id
	@ApiModelProperty(value = "unique id field of task object")
	private int id;
	@ApiModelProperty(value = "name field of task object")
	private String name;
	@ApiModelProperty(value = "description field of task object")
	private String desc;
	@ApiModelProperty(value = "priority (HIGH, MEDIUM, LOW) field of task object")
	private String priority;
	@ApiModelProperty(value = "isCompleted field showing whether the task has been done or not")
	private boolean isCompleted;
	@ApiModelProperty(value = "createdBy field showing who created the task")
	private String createdBy;
}