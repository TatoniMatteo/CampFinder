package com.tatonimatteo.campfinder.service;

import com.tatonimatteo.campfinder.entity.User;
import com.tatonimatteo.campfinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }
}
