package com.example.todo_hitsumabushi.repository;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddRepository extends JpaRepository<Task, Integer> {
    List<TaskForm> findAllByOrderByUpdatedDateDesc();
}
