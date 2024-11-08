package br.eng.jonathan.geriluh_api.service;

import br.eng.jonathan.geriluh_api.model.User;
import br.eng.jonathan.geriluh_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Page<User> listAllUsers(Pageable pagination) {
        return repository.findAll(pagination);
    }

//    public User findUserById(Long userId) {
//        return repository.findById(userId).orElseThrow(() -> new NotFoundException(getMessageErro(USER_FIND_USER_ERRO)));
//    }
}
