package ws.beauty.salon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ws.beauty.salon.dto.ClientRequest;
import ws.beauty.salon.dto.ClientResponse;

@Service
@Transactional
public interface  ClientService {

    List<ClientResponse> findAll();
    List<ClientResponse> findAll(int page, int pageSize);
    ClientResponse findById(Integer id);
    ClientResponse create(ClientRequest request);
    ClientResponse update(Integer id, ClientRequest request);
    void delete(Integer id);
    List<ClientResponse> findByName(String name);
    ClientResponse findByEmail(String email);

}


