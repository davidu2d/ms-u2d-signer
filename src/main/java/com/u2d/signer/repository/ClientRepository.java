package com.u2d.signer.repository;

import com.u2d.signer.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface ClientRepository extends JpaRepository<Client, String> {
}
