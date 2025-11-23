package ws.beauty.salon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ws.beauty.salon.model.AppointmentNote;

@Repository
public interface AppointmentNoteRepository extends JpaRepository<AppointmentNote, Integer> {
    //List<AppointmentNote> findByAppointmentId(Integer appointmentId);
}
