package com.novko.security;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    Optional<User> findById(Long id);
    Page<User> findAll(Predicate predicate, Pageable pageable);
    Page<User> findAll(Pageable pageable);
    User findByUsername(String username);
    List<User> findAll();
    void deleteByUsername(String username);

}
