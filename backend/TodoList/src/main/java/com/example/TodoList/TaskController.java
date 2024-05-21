package com.example.TodoList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	@PostMapping
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
		Task task = taskRepository.findById(id).orElseThrow();
		task.setTitle(taskDetails.getTitle());
		task.setCompleted(taskDetails.IsCompleted());
		final Task updatedTask = taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
	}
	
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
		taskRepository.deleteById(id);
	}
}
