package com.tom.mycat.repository;

import com.tom.mycat.entity.User;
import com.tom.mycat.entity.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    @Query("select new com.tom.mycat.entity.dto.UserDto(u.id,u.name) from User u join Image i where u.image_id=i.id")
    List<UserDto> getList();
}
