package com.novko.internal.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatusFalse();

    @Query("select o from Order o where o.user.username = :username")
    List<Order> findByLoggedUserUsername(String username);

}
