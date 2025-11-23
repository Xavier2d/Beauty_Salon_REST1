package ws.beauty.salon.graphql;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ws.beauty.salon.dto.ServiceRequest;
import ws.beauty.salon.dto.ServiceResponse;
import ws.beauty.salon.service.ServiceService;

@Controller
public class ServiceGraphql {

    @Autowired
    private ServiceService service;

    // -------------------- Queries --------------------

    @QueryMapping
    public List<ServiceResponse> findAllServices() {
        return service.findAll();
    }

    @QueryMapping
    public ServiceResponse findServiceById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<ServiceResponse> findServicesByName(@Argument String name) {
        return service.findByServiceName(name);
    }

    @QueryMapping
    public List<ServiceResponse> findServicesByCategory(@Argument Integer categoryId) {
        return service.findByCategoryId(categoryId);
    }

    // -------------------- Mutations --------------------

    @MutationMapping
    public ServiceResponse createService(@Valid @Argument ServiceRequest req) {
        return service.create(req);
    }

    @MutationMapping
    public ServiceResponse updateService(@Argument Integer id, @Valid @Argument ServiceRequest req) {
        return service.update(id, req);
    }

    @MutationMapping
    public Boolean deleteService(@Argument Integer id) {
        service.delete(id);
        return true;
    }
}
