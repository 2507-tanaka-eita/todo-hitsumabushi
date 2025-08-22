package com.example.todo_hitsumabushi.repository;

import com.example.todo_hitsumabushi.repository.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopRepository extends JpaRepository<Tasks, Integer> {
}
