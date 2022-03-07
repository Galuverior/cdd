package com.github.galuverior.cdd.client.resource;

import com.github.galuverior.cdd.client.dto.ClientDTO;
import com.github.galuverior.cdd.client.entity.Client;
import com.github.galuverior.cdd.client.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("cliente")
public class ClientResource {

    private final ClientService clientService;

    public  ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody ClientDTO clientDTO) {

        Client client = clientService.registerClient(clientDTO);

        return Objects.nonNull(client) ? ResponseEntity.ok().body(client) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> queryClient(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.getClientById(id);

        if (optionalClient.isPresent()) {
            return ResponseEntity.ok().body(optionalClient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
