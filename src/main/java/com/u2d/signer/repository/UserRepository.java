package com.u2d.signer.repository;

import com.u2d.signer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.Optional;

@Eager
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpfCnpj(String cpfCnpj);
}
