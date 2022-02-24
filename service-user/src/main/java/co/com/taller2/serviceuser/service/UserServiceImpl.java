package co.com.taller2.serviceuser.service;


import co.com.taller2.serviceuser.entities.User;
import co.com.taller2.serviceuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(User user) {
        userRepository.delete(user);

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) { return userRepository.findById(id).orElse(null);}
}
