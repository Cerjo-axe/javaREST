package com.sergioluiz.simpleAPI.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergioluiz.simpleAPI.Models.Task;
import com.sergioluiz.simpleAPI.Models.User;
import com.sergioluiz.simpleAPI.Repositories.ITaskRepository;
import com.sergioluiz.simpleAPI.Repositories.IUserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

    }

    public List<Task> findAllByUserId(Long userid) {
        List<Task> tasks = this.taskRepository.findByUser_Id(userid);
        return tasks;

    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id) {
        Task task = findById(id);
        try {
            this.taskRepository.delete(task);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel deletar a Task");
        }
    }
}
