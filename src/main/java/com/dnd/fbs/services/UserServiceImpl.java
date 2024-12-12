package com.dnd.fbs.services;

import com.dnd.fbs.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dnd.fbs.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositories ur;

    @Override
    public User save(String username, String password, int role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return ur.save(user);
    }

    @Override
    public Page<User> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return ur.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return ur.findById(userId);
    }

    @Override
    public User save(User user) {
        return ur.save(user);
    }

    @Override
    public void deleteById(int userId) {
        ur.deleteById(userId);
    }

    @Override
    public User getAccount(String username) {
        return ur.findByUsername(username);
    }

    @Override
    public void addAccount(User user) {
        ur.save(user);
    }
}
