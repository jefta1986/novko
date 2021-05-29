package com.novko.internal.orders;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {

    List<Order> findByStatusFalse();

    @Query("select o from Order o where o.user.username = :username")
    List<Order> findByLoggedUserUsername(String username);

    Page<Order> findAll(Predicate predicate, Pageable pageable);
    Page<Order> findAll(Pageable pageable);

}
