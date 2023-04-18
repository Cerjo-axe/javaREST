package com.sergioluiz.simpleAPI.Services;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergioluiz.simpleAPI.Models.User;
import com.sergioluiz.simpleAPI.Repositories.ITaskRepository;
import com.sergioluiz.simpleAPI.Repositories.IUserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITaskRepository taskRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    @Transactional
    public User create(User obj) {
        obj.setIdobj.getPassword();
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findById((obj.getId()));
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir as entidades");
        }
    }
}
