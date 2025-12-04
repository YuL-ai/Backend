package com.usuarios.Demo.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usuarios.Demo.model.AdminModel;

@Repository
public interface IAdminModelRepository extends JpaRepository<AdminModel, UUID> {

    Optional<AdminModel> findByEmail(String email);

}

