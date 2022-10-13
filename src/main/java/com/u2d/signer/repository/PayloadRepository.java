package com.u2d.signer.repository;

import com.u2d.signer.entity.Payload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface PayloadRepository extends JpaRepository<Payload, Long> {
}
