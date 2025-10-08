package com.Project.BookStore.Repository;

import com.Project.BookStore.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    public Users findByUsername(String username);

    public boolean existsByUsername(String username);
}
