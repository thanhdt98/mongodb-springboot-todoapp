package com.thanhxv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanhxv.exception.TodoCollectionException;
import com.thanhxv.model.TodoDTO;
import com.thanhxv.repository.TodoRepository;
import com.thanhxv.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Override
	public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException {
		Optional<TodoDTO> todoOptional = todoRepository.findByTodo(todoDTO.getTodo());
		if (todoOptional.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.todoAlreadyExists());
		} else {
			todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepository.save(todoDTO);
		}
	}

	@Override
	public List<TodoDTO> findAll() {
		List<TodoDTO> todos = todoRepository.findAll();
		return todos.size() > 0 ? todos : new ArrayList<>();
	}

	@Override
	public TodoDTO findOne(String id) throws TodoCollectionException {
		Optional<TodoDTO> todo = todoRepository.findById(id);
		if (!todo.isPresent()) {
			throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
		}
		return todo.get();
	}

	@Override
	public TodoDTO updateTodo(String id, TodoDTO todoDTO) throws TodoCollectionException {
		Optional<TodoDTO> todoWithId = todoRepository.findById(id);
		if (todoWithId.isPresent()) {
			TodoDTO todoUpdated = new TodoDTO();
			todoUpdated.setId(todoUpdated.getId());
			todoUpdated.setTodo(todoDTO.getTodo() != null ? todoDTO.getTodo() : todoUpdated.getTodo());
			todoUpdated
					.setCompleted(todoDTO.getCompleted() != null ? todoDTO.getCompleted() : todoUpdated.getCompleted());
			todoUpdated.setDescription(
					todoDTO.getDescription() != null ? todoDTO.getDescription() : todoUpdated.getDescription());
			todoUpdated.setUpdatedAt(new Date(System.currentTimeMillis()));
			return todoRepository.save(todoUpdated);
		} else {
			throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
		}

	}

	@Override
	public void deleteTodo(String id) throws TodoCollectionException {
		Optional<TodoDTO> todoWithId = todoRepository.findById(id);
		if (todoWithId.isPresent()) {
			todoRepository.deleteById(id);
		} else {
			throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
		}
	}

}
