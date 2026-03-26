package com.company.attendance.service;

import com.company.attendance.entity.Attendance;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceService {
    void saveAttendance(Attendance attendance);
    List<Attendance> getAllAttendance();
    Optional<Attendance> getAttendanceByEmployeeAndDate(Long employeeId, LocalDate date);
}