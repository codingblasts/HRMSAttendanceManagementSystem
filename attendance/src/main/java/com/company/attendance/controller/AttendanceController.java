package com.company.attendance.controller;

import com.company.attendance.entity.Attendance;
import com.company.attendance.service.AttendanceService;
import com.company.attendance.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final EmployeeRepository employeeRepository;

    public AttendanceController(AttendanceService attendanceService,
                                EmployeeRepository employeeRepository) {
        this.attendanceService = attendanceService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        model.addAttribute("employees", employeeRepository.findAll());
        return "attendance_form";
    }

    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute Attendance attendance, Model model) {
        try {
            attendanceService.saveAttendance(attendance);
            return "redirect:/attendance/list";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("employees", employeeRepository.findAll());
            return "attendance_form";
        }
    }

    @GetMapping("/list")
    public String getAttendance(Model model) {
        model.addAttribute("attendanceList", attendanceService.getAllAttendance());
        return "attendance_list";
    }

    @PostMapping("/checkin")
    public String checkIn(@RequestParam Long employeeId, Model model) {
        try {
            Attendance attendance = attendanceService
                    .getAttendanceByEmployeeAndDate(employeeId, LocalDate.now())
                    .orElse(new Attendance());

            if (attendance.getCheckIn() != null) {
                model.addAttribute("error", "Already checked in today");
                model.addAttribute("employees", employeeRepository.findAll());
                return "attendance_form";
            }

            attendance.setEmployeeId(employeeId);
            attendance.setDate(LocalDate.now());
            attendance.setCheckIn(LocalTime.now());
            attendance.setStatus("Present");

            attendanceService.saveAttendance(attendance);
            return "redirect:/attendance/list";
        } catch (Exception e) {
            model.addAttribute("error", "Error while checking in: " + e.getMessage());
            model.addAttribute("employees", employeeRepository.findAll());
            return "attendance_form";
        }
    }

    @PostMapping("/checkout")
    public String checkOut(@RequestParam Long employeeId, Model model) {
        try {
            Attendance attendance = attendanceService
                    .getAttendanceByEmployeeAndDate(employeeId, LocalDate.now())
                    .orElse(null);

            if (attendance == null || attendance.getCheckIn() == null) {
                model.addAttribute("error", "No check-in found for today. Please check in first.");
                model.addAttribute("employees", employeeRepository.findAll());
                return "attendance_form";
            }

            if (attendance.getCheckOut() != null) {
                model.addAttribute("error", "Already checked out today");
                model.addAttribute("employees", employeeRepository.findAll());
                return "attendance_form";
            }

            attendance.setCheckOut(LocalTime.now());
            attendanceService.saveAttendance(attendance);
            return "redirect:/attendance/list";
        } catch (Exception e) {
            model.addAttribute("error", "Error while checking out: " + e.getMessage());
            model.addAttribute("employees", employeeRepository.findAll());
            return "attendance_form";
        }
    }
}
