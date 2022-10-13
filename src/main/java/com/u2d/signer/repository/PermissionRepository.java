package com.u2d.signer.repository;

import com.u2d.signer.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import java.util.Optional;

@Eager
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByDescription(String description);
}
