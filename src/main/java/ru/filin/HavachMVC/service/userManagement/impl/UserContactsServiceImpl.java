package ru.filin.HavachMVC.service.userManagement.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.userManagement.entities.UserContacts;
import ru.filin.HavachMVC.model.userManagement.repositories.UserContactsRepository;
import ru.filin.HavachMVC.service.userManagement.UserContactsService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserContactsServiceImpl implements UserContactsService {

    private UserContactsRepository userContactsRepository;

    @Autowired
    public UserContactsServiceImpl(UserContactsRepository userContactsRepository) {
        this.userContactsRepository = userContactsRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserContactsServiceImpl.class);

    @Override
    public List<UserContacts> getAll() {
        List<UserContacts> userContacts = userContactsRepository.getAll();
        logger.info("get userContacts " + userContacts.stream().collect(Collectors.toMap(UserContacts::getId, Function.identity())));
        return userContacts;
    }

    @Override
    public UserContacts getById(long id) {
        logger.info("get user contacts by id " + id);
        return userContactsRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        logger.info("delete user contacts=" + id);
        userContactsRepository.delete(id);
    }

    @Override
    public void update(UserContacts obj) {
        logger.info("update user contacts id=" + obj.getId());
        userContactsRepository.update(obj);
    }

    @Override
    public Long save(UserContacts obj) {
        logger.info("add contacts + userId=" + obj.getUserId() + "address=" +obj.getAddress() + " phone=" + obj.getPhone());
        return userContactsRepository.save(obj);
    }

    @Override
    public UserContacts getByUserId(long id) {
        UserContacts byUserId = null;
        try {
            logger.info("get user contact by userId=" + id);
            byUserId = userContactsRepository.getByUserId(id);
        } catch (EmptyResultDataAccessException e) {

        }
        return byUserId;
    }
}
