package com.example.todo_hitsumabushi.repository;

import com.example.todo_hitsumabushi.repository.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    // タスクを取得するSQL文（日付、ステータス、タスク内容の絞り込み機能に対応）
    @Query("SELECT t FROM Task t " +
            "WHERE t.limitDate BETWEEN :start AND :end " +
            "AND ((:searchword IS NULL OR :status = 0 ) OR t.status = :status) " +
            "AND ((:searchword IS NULL OR :searchword = '') OR t.content LIKE CONCAT(:searchword, '%')) " +
            "ORDER BY t.limitDate ASC")
    List<Task> findFilterTasks(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("status") Integer status,
            @Param("searchword") String searchword,
            Pageable pageable
    );
}
