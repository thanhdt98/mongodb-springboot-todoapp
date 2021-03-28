package com.thanhxv.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.thanhxv.validation.constraint.CheckSizeConstraint;

import lombok.Data;

@Data
@Document(collection = "todos")
public class TodoDTO {

	@Id
	private String id;
	
//	@CheckSizeConstraint
	@NotNull(message = "todo cannot be null")
	private String todo;
	
	@NotNull(message = "description cannot be null")
	private String description;
	
	private Boolean completed;
	private Date createdAt;
	private Date updatedAt;

	public TodoDTO() {
		super();
	}

	protected TodoDTO(String id, String todo, String description, Boolean completed, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.todo = todo;
		this.description = description;
		this.completed = completed;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
