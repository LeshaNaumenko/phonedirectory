package com.course.phonedirectory.rep;

import com.course.phonedirectory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);

    @Override
    void deleteById(Integer integer);
}
