
package com.studysprint.backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final StudyTaskRepository repository;

    public TaskController(StudyTaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<StudyTask> getAllTasks() {
        return repository.findAll();
    }

    @PostMapping
    public StudyTask addTask(@RequestBody StudyTask task) {
        task.setCompleted(false);
        return repository.save(task);
    }

    @PutMapping("/{id}/complete")
    public StudyTask completeTask(@PathVariable Long id) {
        StudyTask task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setCompleted(true);
        return repository.save(task);
    }
}
