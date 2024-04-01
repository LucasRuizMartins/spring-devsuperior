package com.clientManagement.clientmanagement.dto;

import com.clientManagement.clientmanagement.entities.Client;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class ClientDTO {

    Long id;

    @NotBlank(message = "Campo Obrigatório")
    String name;
    String cpf;

    Double income;
    LocalDate birthDate;
    Integer children;

    public ClientDTO(){

    }
    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
