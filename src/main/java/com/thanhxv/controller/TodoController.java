package com.thanhxv.controller;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thanhxv.exception.TodoCollectionException;
import com.thanhxv.model.TodoDTO;
import com.thanhxv.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodos() {
		List<TodoDTO> todos = todoService.findAll();
		return new ResponseEntity<List<TodoDTO>>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(todoService.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
		try {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoService.createTodo(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<?> updateSingleTodo(@PathVariable("id") String id, @RequestBody TodoDTO todoDTO) {
		try {
			TodoDTO todoToSave = todoService.updateTodo(id, todoDTO);
			return new ResponseEntity<TodoDTO>(todoToSave, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Todo not found id " + id, HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping("/todos/{id}")
	public ResponseEntity<?> patchSingleTodo(@PathVariable("id") String id, @RequestBody TodoDTO todoDTO) {
		try {
			TodoDTO todoToSave = todoService.updateTodo(id, todoDTO);
			return new ResponseEntity<TodoDTO>(todoToSave, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Todo not found id " + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {
		try {
			todoService.deleteTodo(id);
			return new ResponseEntity("Successfully deleted withd id " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
