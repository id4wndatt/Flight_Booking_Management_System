package com.dnd.fbs.services;

import com.dnd.fbs.models.User;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.Optional;

@Service
public interface UserService {
    public void addAccount(User user);
    public User getAccount(String username);
    public User save(String username, String password, int role);
    public Page<User> findAll(int pageNo, int pageSize, String sortBy, String sortDir);

    Optional<User> findById(Integer userId);

    User save(User user);

    void deleteById(int userId);
}
