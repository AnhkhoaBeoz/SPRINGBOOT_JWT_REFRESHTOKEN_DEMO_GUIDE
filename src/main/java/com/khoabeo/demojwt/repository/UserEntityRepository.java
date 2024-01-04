package com.khoabeo.demojwt.repository;

import com.khoabeo.demojwt.modal.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByUsername(String username);

    List<UserEntity> findAllByUsername(String userName);
}
