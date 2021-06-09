package com.novko.security;

import com.novko.api.request.Query;
import com.novko.api.request.UserFilter;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Page<User> findAllOrFiltered(Query query) {
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getSize(),
                Sort.by(new Sort.Order(Sort.Direction.fromString(query.getSortDirection().toUpperCase()), query.getSortProperty(), Sort.NullHandling.NULLS_LAST)));
        UserFilter filter = (UserFilter) query.getFilter();

        Predicate predicate = buildPredicate(filter);

        Page<User> users;

        if (predicate != null) {
            users = userRepository.findAll(predicate, pageRequest);
        } else {
            users = userRepository.findAll(pageRequest);
        }

        if (users == null) {
            return null;
        }

        return users;
    }

    private Predicate buildPredicate(UserFilter filter) {
        List<Predicate> expressions = new LinkedList<>();
        if (filter != null) {
            if (filter.getActive() != null) {
                expressions.add(
                        QUser.user.active.eq(filter.getActive()));
            }
            if (filter.getEmailPart() != null && !filter.getEmailPart().trim().isEmpty()) {
                expressions.add(
                        QUser.user.username.containsIgnoreCase(filter.getEmailPart()));
            }
            if (filter.getPibPart() != null && !filter.getPibPart().trim().isEmpty()) {
                expressions.add(
                        QUser.user.pib.containsIgnoreCase(filter.getPibPart()));
            }
            if (filter.getMbPart() != null && !filter.getMbPart().trim().isEmpty()) {
                expressions.add(
                        QUser.user.mb.containsIgnoreCase(filter.getMbPart()));
            }
        }
        return ExpressionUtils.allOf(expressions);
    }
}
