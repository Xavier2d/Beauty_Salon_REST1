package ws.beauty.salon.graphql;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.AppointmentNoteRequest;
import ws.beauty.salon.dto.AppointmentNoteResponse;
import ws.beauty.salon.service.AppointmentNoteService;

@Controller
public class AppointmentNoteGraphql {

    @Autowired
    private AppointmentNoteService service;

    //  Obtener todas las notas
    /*@QueryMapping
    public List<AppointmentNoteResponse> findAllNotes() {
        return service.findAll();
    }*/

    //  Obtener nota por ID
    @QueryMapping
    public AppointmentNoteResponse findNoteById(@Argument Integer idNote) {
        return service.findById(idNote);
    }

    //  Crear nueva nota
    @MutationMapping
    public AppointmentNoteResponse createNote(@Valid @Argument AppointmentNoteRequest req) {
        return service.create(req);
    }

    //  Actualizar nota existente
    @MutationMapping
    public AppointmentNoteResponse updateNote(
            @Argument Integer idNote,
            @Valid @Argument AppointmentNoteRequest req) {
        return service.update(idNote, req);
    }

    //  Eliminar nota
    /*@MutationMapping
    public Boolean deleteNote(@Argument Integer idNote) {
        service.delete(idNote);
        return true;
    }*/
}
