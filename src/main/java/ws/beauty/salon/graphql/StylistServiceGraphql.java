package ws.beauty.salon.graphql;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ws.beauty.salon.dto.StylistServiceRequest;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.service.StylistServiceService;

@Controller
public class StylistServiceGraphql {

    @Autowired
    private StylistServiceService service;

    // -------------------- Queries --------------------

    @QueryMapping
    public List<StylistServiceResponse> findAllStylistServices() {
        return service.findAll();
    }

    @QueryMapping
    public StylistServiceResponse findStylistServiceById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<StylistServiceResponse> findStylistServicesByStylist(@Argument Integer stylistId) {
        return service.findByStylist(stylistId);
    }

    @QueryMapping
    public List<StylistServiceResponse> findStylistServicesByService(@Argument Integer serviceId) {
        return service.findByService(serviceId);
    }

    // -------------------- Mutations --------------------

    @MutationMapping
    public StylistServiceResponse createStylistService(@Valid @Argument StylistServiceRequest req) {
        return service.create(req);
    }

    @MutationMapping
    public Boolean deleteStylistService(@Argument Integer id) {
        service.delete(id);
        return true;
    }
}
