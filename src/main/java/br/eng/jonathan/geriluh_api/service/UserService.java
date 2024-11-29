package br.eng.jonathan.geriluh_api.service;

import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.User;
import br.eng.jonathan.geriluh_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String USER_FIND_USER_ERRO = "USER.SEARCH_USER_ERROR";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository repository;

    public Page<User> listAllUsers(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public User findUserById(Long userId) throws NotFoundException {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException(getMessageErro()));
    }

    public User createUser(User user) throws NotFoundException {
        return repository.save(user);
    }

    private String getMessageErro() {
        return messageSource.getMessage(UserService.USER_FIND_USER_ERRO, null, LocaleContextHolder.getLocale());
    }
}
