package com.sergioluiz.simpleAPI.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sergioluiz.simpleAPI.Models.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);
}
