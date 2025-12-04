package com.usuarios.Demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuarios.Demo.model.ReservaModel;
import com.usuarios.Demo.model.UserModel;
import com.usuarios.Demo.model.PapaModel;

@Repository
public interface IReservaRepository extends JpaRepository<ReservaModel, UUID> {

    List<ReservaModel> findByUsuario(UserModel usuario);

    List<ReservaModel> findByPapa(PapaModel papa);

    Optional<UserModel> findByEmail(String email);

    List<ReservaModel> findByFechaVisita(LocalDate fechaVisita);

    List<ReservaModel> findByPapaAndFechaVisita(PapaModel papa, LocalDate fechaVisita);
}
