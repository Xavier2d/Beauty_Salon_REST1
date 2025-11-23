package ws.beauty.salon.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.PriceHistoryRequest;
import ws.beauty.salon.dto.PriceHistoryResponse;
import ws.beauty.salon.service.PriceHistoryService;

@Controller
public class PriceHistoryGraphql {

    @Autowired
    private PriceHistoryService service;

    // Consultas
    @QueryMapping
    public List<PriceHistoryResponse> findAllPriceHistories() {
        return service.findAll();
    }

    @QueryMapping
    public PriceHistoryResponse findPriceHistoryById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<PriceHistoryResponse> findPriceHistoriesByService(@Argument Integer serviceId) {
        return service.findByService(serviceId);
    }

    @QueryMapping
    public List<PriceHistoryResponse> findPriceHistoriesByUser(@Argument Integer userId) {
        return service.findByUser(userId);
    }

    // Mutaciones
    @MutationMapping
    public PriceHistoryResponse createPriceHistory(@Valid @Argument PriceHistoryRequest request) {
        return service.create(request);
    }

    @MutationMapping
    public PriceHistoryResponse updatePriceHistory(@Argument Integer id, @Valid @Argument PriceHistoryRequest request) {
        return service.update(id, request);
    }

    @MutationMapping
    public Boolean deletePriceHistory(@Argument Integer id) {
        service.delete(id);
        return true;
    }
}

