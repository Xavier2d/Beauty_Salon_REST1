package ws.beauty.salon.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.ClientRequest;
import ws.beauty.salon.dto.ClientResponse;
import ws.beauty.salon.service.ClientService;

@Controller
public class ClientGraphql {

    @Autowired
    private ClientService service;

    // Obtener todos los clientes
    @QueryMapping
    public List<ClientResponse> findAllClients() {
        return service.findAll();
    }

    // Obtener clientes paginados
    @QueryMapping
    public List<ClientResponse> findAllClientsPaged(
            @Argument int page,
            @Argument int pageSize) {
        return service.findAll(page, pageSize);
    }

    // Buscar cliente por ID
    @QueryMapping
    public ClientResponse findClientById(@Argument Integer id) {
        return service.findById(id);
    }

    // Buscar cliente por correo
    @QueryMapping
    public ClientResponse findClientByEmail(@Argument String email) {
        return service.findByEmail(email);
    }

    // Buscar clientes por nombre
    @QueryMapping
    public List<ClientResponse> findClientsByName(@Argument String name) {
        return service.findByName(name);
    }

    // Crear cliente
    @MutationMapping
    public ClientResponse createClient(@Valid @Argument ClientRequest request) {
        return service.create(request);
    }

    // Actualizar cliente
    @MutationMapping
    public ClientResponse updateClient(
            @Argument Integer id,
            @Valid @Argument ClientRequest request) {
        return service.update(id, request);
    }

    // Eliminar cliente
    @MutationMapping
    public Boolean deleteClient(@Argument Integer id) {
        service.delete(id);
        return true;
    }
}

