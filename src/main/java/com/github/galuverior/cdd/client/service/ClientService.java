package com.github.galuverior.cdd.client.service;

import com.github.galuverior.cdd.client.dto.ClientDTO;
import com.github.galuverior.cdd.client.entity.Client;
import com.github.galuverior.cdd.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client registerClient(ClientDTO clientDTO) {
        try {
            Client client = new Client(clientDTO.getId(), clientDTO.getNome(), clientDTO.getEmail(), clientDTO.getSenha());
            return clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean validateClientCredentials(String email, String senha) {
        return Objects.nonNull(clientRepository.validateClientCredentials(email, senha) ? true : false);
    }
}




