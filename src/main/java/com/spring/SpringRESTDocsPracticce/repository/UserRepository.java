package com.spring.SpringRESTDocsPracticce.repository;

import com.spring.SpringRESTDocsPracticce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
