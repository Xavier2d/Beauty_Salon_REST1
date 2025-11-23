package ws.beauty.salon.service;


import java.time.LocalDateTime;
import java.util.List;

import ws.beauty.salon.dto.AttendanceLogRequest;
import ws.beauty.salon.dto.AttendanceLogResponse;

public interface AttendanceLogService {
    List<AttendanceLogResponse> findAll();

    AttendanceLogResponse findById(Integer idAttendance);

    AttendanceLogResponse create(AttendanceLogRequest request);

    AttendanceLogResponse update(Integer idAttendance, AttendanceLogRequest request);

    void delete(Integer idAttendance);

    List<AttendanceLogResponse> findByStylistId(Integer stylistId);

    List<AttendanceLogResponse> findByCheckInBetween(LocalDateTime start, LocalDateTime end);

    boolean hasOpenAttendance(Integer stylistId);

    AttendanceLogResponse closeAttendance(Integer stylistId);

    List<AttendanceLogResponse> findAll(int page, int pageSize);

    long countByStylistId(Integer stylistId);

    long countByCheckInBetween(LocalDateTime start, LocalDateTime end);
    
}
