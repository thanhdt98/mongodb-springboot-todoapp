package com.thanhxv.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.thanhxv.exception.TodoCollectionException;
import com.thanhxv.model.TodoDTO;

public interface TodoService {

	public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException;

	public List<TodoDTO> findAll();

	public TodoDTO findOne(String id) throws TodoCollectionException;

	public TodoDTO updateTodo(String id, TodoDTO todoDTO) throws TodoCollectionException;

	public void deleteTodo(String id) throws TodoCollectionException;

}
