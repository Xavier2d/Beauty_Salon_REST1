package ws.beauty.salon.service;

import jakarta.persistence.EntityNotFoundException;
import ws.beauty.salon.dto.ClientRequest;
import ws.beauty.salon.dto.ClientResponse;
import ws.beauty.salon.mapper.ClientMapper;
import ws.beauty.salon.repository.ClientRepository;
import ws.beauty.salon.model.Client;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
@SuppressWarnings("null")
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    // 🔹 Obtener todos los clientes
    @Override
    public List<ClientResponse> findAll() {
        return repository.findAll().stream()
                .map(ClientMapper::toResponse)
                .toList();
    }

    // 🔹 Obtener todos los clientes con paginación
    @Override
    public List<ClientResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Client> clients = repository.findAll(pageReq);
        return clients.getContent().stream()
                .map(ClientMapper::toResponse)
                .toList();
    }

    // 🔹 Obtener cliente por su ID
    @Override
    public ClientResponse findById(Integer id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
        return ClientMapper.toResponse(client);
    }

    // 🔹 Crear un nuevo cliente
    @Override
    public ClientResponse create(ClientRequest request) {
        Client entity = ClientMapper.toEntity(request);
        Client saved = repository.save(entity);
        return ClientMapper.toResponse(saved);
    }

    // 🔹 Actualizar cliente existente
    @Override
    public ClientResponse update(Integer id, ClientRequest request) {
        Client existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));

        ClientMapper.copyToEntity(request, existing);
        Client updated = repository.save(existing);
        return ClientMapper.toResponse(updated);
    }

    // 🔹 Eliminar cliente
    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Client not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    // 🔹 Buscar clientes por nombre
    @Override
    public List<ClientResponse> findByName(String name) {
        return repository.findByNameIgnoreCase(name).stream()
                .map(ClientMapper::toResponse)
                .toList();
    }

    // 🔹 Buscar cliente por correo
    @Override
    public ClientResponse findByEmail(String email) {
        Client client = repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with email: " + email));
        return ClientMapper.toResponse(client);
    }

    
}
