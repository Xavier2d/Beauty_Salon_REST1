package ws.beauty.salon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.beauty.salon.dto.AppointmentNoteRequest;
import ws.beauty.salon.dto.AppointmentNoteResponse;
import ws.beauty.salon.service.AppointmentNoteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment-notes")
@RequiredArgsConstructor
@Tag(
    name = "Appointment Notes",
    description = "API for managing notes associated with appointments. Allows creating, reading, updating, and deleting notes."
)
public class AppointmentNoteController {

    private final AppointmentNoteService noteService;

    // ───────────────────────────────────────────────────────────────
    @Operation(
        summary = "Get appointment notes with pagination",
        description = "Retrieves appointment notes using pagination parameters `page` and `size`.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Paginated notes retrieved successfully",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AppointmentNoteResponse.class)))
        }
    )
    @GetMapping("/paginated")
    public ResponseEntity<List<AppointmentNoteResponse>> getAllPaginated(
            @Parameter(description = "Page number (starts from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(noteService.findAllPaginated(page, size));
    }

    // ───────────────────────────────────────────────────────────────
    @Operation(
        summary = "Get an appointment note by ID",
        description = "Retrieves a specific appointment note by its unique ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Note found",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AppointmentNoteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Note not found")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentNoteResponse> getById(
            @Parameter(description = "Note ID", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(noteService.findById(id));
    }

    // ───────────────────────────────────────────────────────────────
    @Operation(
        summary = "Create a new appointment note",
        description = "Registers a new note associated with an existing appointment.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "New note data",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Create Note Example",
                    value = """
                        {
                            "idAppointment": 2,
                            "noteText": "The client arrived 10 minutes early and requested a classic haircut."
                        }
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Note successfully created",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AppointmentNoteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Appointment not found")
        }
    )
    @PostMapping
    public ResponseEntity<AppointmentNoteResponse> create(@Valid @RequestBody AppointmentNoteRequest request) {
        return ResponseEntity.ok(noteService.create(request));
    }

    // ───────────────────────────────────────────────────────────────
    @Operation(
        summary = "Update an existing appointment note",
        description = "Updates an existing note by its ID.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Updated note data",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    name = "Update Note Example",
                    value = """
                        {
                            "idAppointment": 2,
                            "noteText": "The client requested a hairstyle change at the end of the service."
                        }
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Note successfully updated",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AppointmentNoteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Note or appointment not found")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentNoteResponse> update(
            @Parameter(description = "ID of the note to update", example = "3") @PathVariable Integer id,
            @Valid @RequestBody AppointmentNoteRequest request) {
        return ResponseEntity.ok(noteService.update(id, request));
    }

    // ───────────────────────────────────────────────────────────────
    /*@Operation(
        summary = "Delete an appointment note by ID",
        description = "Deletes a specific appointment note from the database.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Note successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Note not found")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the note to delete", example = "5") @PathVariable Integer id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }*/
}

