package ws.beauty.salon.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.RescheduleRequestRequest;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.service.RescheduleRequestService;

@Controller
public class RescheduleRequestGraphql {

    @Autowired
    private RescheduleRequestService service;

    // Consultas
    @QueryMapping
    public List<RescheduleRequestResponse> findAllRescheduleRequests() {
        return service.findAll();
    }

    @QueryMapping
    public RescheduleRequestResponse findRescheduleRequestById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<RescheduleRequestResponse> findRescheduleRequestsByClient(@Argument Integer clientId) {
        return service.findByClient(clientId);
    }

    @QueryMapping
    public List<RescheduleRequestResponse> findRescheduleRequestsByStatus(@Argument String status) {
        return service.findByStatus(status);
    }

    // Mutaciones
    @MutationMapping
    public RescheduleRequestResponse createRescheduleRequest(@Valid @Argument RescheduleRequestRequest request) {
        return service.create(request);
    }

    @MutationMapping
    public RescheduleRequestResponse updateRescheduleRequest(@Argument Integer id, @Valid @Argument RescheduleRequestRequest request) {
        return service.update(id, request);
    }

    @MutationMapping
    public Boolean deleteRescheduleRequest(@Argument Integer id) {
        service.delete(id);
        return true;
    }
}
