package com.clientManagement.clientmanagement.service;


import com.clientManagement.clientmanagement.dto.ClientDTO;
import com.clientManagement.clientmanagement.entities.Client;
import com.clientManagement.clientmanagement.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

@Autowired
    private ClientRepository repository;

@Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
    Client client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException("Cliente Não encontrado"));
    return  new ClientDTO(client);
}

@Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients = repository.findAll(pageable);
        return clients.map(cli -> new ClientDTO(cli));
}

    @Transactional
    public ClientDTO insert (ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO((entity));
        } catch (EntityNotFoundException e) {
            throw new ClientNotFoundException("Cliente Não encontrado");
        }

    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());

    }
}
