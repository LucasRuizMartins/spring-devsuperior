package com.clientManagement.clientmanagement.repositories;

import com.clientManagement.clientmanagement.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCpf(String cpf);
}
