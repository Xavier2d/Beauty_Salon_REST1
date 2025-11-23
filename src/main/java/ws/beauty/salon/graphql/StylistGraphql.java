package ws.beauty.salon.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.StylistRequest;
import ws.beauty.salon.dto.StylistResponse;
import ws.beauty.salon.service.StylistService;

@Controller
public class StylistGraphql {

    @Autowired
    private StylistService service;

    // 🔹 Query: obtener todos los estilistas
    @QueryMapping
    public List<StylistResponse> findAll() {
        return service.findAll();
    }

    //  Query: obtener estilista por ID
    @QueryMapping
    public StylistResponse findById(@Argument Integer idStylist) {
        return service.findById(idStylist);
    }

    //  Mutation: crear nuevo estilista
    @MutationMapping
    public StylistResponse create(@Valid @Argument StylistRequest req) {
        return service.create(req);
    }

    // Mutation: actualizar estilista
    @MutationMapping
    public StylistResponse update(@Argument Integer idStylist, @Valid @Argument StylistRequest req) {
        return service.update(idStylist, req);
    }

    //  Mutation: eliminar estilista
   
}

