package com.example.todo_hitsumabushi.service;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.repository.TaskRepository;
import com.example.todo_hitsumabushi.repository.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddService {
    @Autowired
    TaskRepository taskRepository;

    //タスク追加
    @Transactional
    public void saveTask(TaskForm reqTask){
        Task saveTask = setTaskEntity(reqTask);
        System.out.println(saveTask.getContent());
        taskRepository.save(saveTask);
    }

    private Task setTaskEntity(TaskForm form){
        Task task = new Task();
        task.setContent(form.getContent());
        task.setLimitDate(form.getLimitDate());
        task.setStatus(1);
        task.setCreatedDate(LocalDateTime.now());
        task.setUpdatedDate(LocalDateTime.now());
        return task;
    }

    public List<Task> findAllcontent() {
        return taskRepository.findAllByOrderByUpdatedDateDesc();
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
