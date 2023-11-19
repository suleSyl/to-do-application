package com.todoapp.taskmanager.models;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@ApiModel(value = "Task Model Documentation", description = "Model")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	@ApiModelProperty(value = "unique id field of task object")
	private String id;
	@ApiModelProperty(value = "description field of task object")
	private String desc;
	@ApiModelProperty(value = "isCompleted field showing whether the task has been done or not")
	private boolean isCompleted;
	@ApiModelProperty(value = "createdBy field showing who created the task")
	private String createdBy;

	public Task(String desc, boolean isCompleted, String createdBy) {
		this.desc = desc;
		this.isCompleted = isCompleted;
		this.createdBy = createdBy;
	}
}