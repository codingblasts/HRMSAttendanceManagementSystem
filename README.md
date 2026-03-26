# HRMSAttendanceManagementSystem
Built a full HRMS from scratch. No tutorials. No shortcuts. Just code.   Employee management  Real-time check-in &amp; check-out  Auto Late detection after 10:00 AM  Live dashboard with today's stats  Clean, modern UI - built with Spring Boot + Thymeleaf  This isn't a clone. This isn't a course project. 
# 🏢 HRMS — HR Management System

A full-stack **HR Management System** built with **Java Spring Boot**, **MySQL**, and **Thymeleaf**. Manage employees, track daily attendance, auto-detect late arrivals, and monitor everything from a live dashboard.

---

## 🚀 Live Features

### 👥 Employee Management
- Add employees with name, email, and department
- View all employees in a clean, modern table
- Auto-generated avatar initials from employee name
- Delete any employee — their attendance records are automatically removed too

### 🕐 Attendance System
- One-click **Check In** — records exact timestamp
- One-click **Check Out** — tracks departure time
- Prevents duplicate check-in on the same day
- Prevents check-out without a prior check-in
- Employee name saved directly on every attendance record

### ⚡ Auto Late Detection
- Office timing: **9:45 AM**
- System automatically marks **"Late"** if check-in is after **10:00 AM**
- No manual work needed — fully automatic
- Color-coded status pills:
  - 🟢 **Present** — checked in on time
  - 🟡 **Late** — checked in after 10:00 AM
  - 🔴 **Absent** — no check-in recorded

### 📊 Live Dashboard
- Total employees registered
- Present today (real-time)
- Late today (updates on every check-in)
- This month's total attendance records
- Quick action cards for one-click navigation

### 📋 Attendance Log
- Full history of all check-ins and check-outs
- Shows employee name instead of just ID
- Check-in time in green, check-out in orange
- Delete any individual attendance record
- Monospace font for clean timestamp display

### 🎨 UI & Design
- Dark navy sidebar with teal accent color
- Smooth fade-up animations on page load
- Active navigation highlighting on every page
- Empty state screens when no data exists
- Confirm popups before any delete action
- Inline error messages — no crashes

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java Spring Boot 3.2 |
| Database | MySQL 8 + Hibernate JPA |
| Frontend | Thymeleaf + Custom CSS |
| Architecture | MVC (Model-View-Controller) |
| Build Tool | Maven |
| Font | DM Sans (Google Fonts) |

---

## 📁 Project Structure

```
attendance/
├── src/
│   ├── main/
│   │   ├── java/com/company/attendance/
│   │   │   ├── controller/
│   │   │   │   ├── AttendanceController.java   # Check-in, check-out, list, delete
│   │   │   │   ├── DashboardController.java    # Live dashboard stats
│   │   │   │   └── EmployeeController.java     # Add, list, delete employees
│   │   │   ├── entity/
│   │   │   │   ├── Attendance.java             # Attendance table model
│   │   │   │   └── Employee.java               # Employee table model
│   │   │   ├── repository/
│   │   │   │   ├── AttendanceRepository.java   # JPA queries for attendance
│   │   │   │   └── EmployeeRepository.java     # JPA queries for employees
│   │   │   └── service/
│   │   │       ├── AttendanceService.java      # Attendance service interface
│   │   │       ├── AttendanceServiceImpl.java  # Attendance service implementation
│   │   │       └── EmployeeService.java        # Employee service
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html                  # Dashboard page
│   │       │   ├── employees.html              # Employee list page
│   │       │   ├── create_employee.html        # Add employee form
│   │       │   ├── attendance_form.html        # Check-in / check-out page
│   │       │   └── attendance_list.html        # Attendance log page
│   │       └── application.properties         # DB config & app settings
└── pom.xml                                    # Maven dependencies
```

---

## ⚙️ Setup & Installation

### Prerequisites
Make sure you have these installed:
- Java 21+
- Maven
- MySQL 8+

### Step 1 — Clone the repository
```bash
git clone https://github.com/your-username/HRMS.git
cd HRMS/attendance
```

### Step 2 — Configure MySQL

Open `src/main/resources/application.properties` and update your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/attendance_system?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password_here
```

> The database `attendance_system` will be **created automatically** on first run.

### Step 3 — Run the application
```bash
./mvnw spring-boot:run
```

### Step 4 — Open in browser
```
http://localhost:8080
```

---

## 📸 Pages Overview

| Page | URL | Description |
|---|---|---|
| Dashboard | `/dashboard` | Live stats — employees, present, late, monthly |
| Employees | `/employees` | View and manage all employees |
| Add Employee | `/employees/new` | Register a new employee |
| Mark Attendance | `/attendance/form` | Check in or check out |
| Attendance Log | `/attendance/list` | Full attendance history |

---

## 🔧 Configuration

### Change the Late Arrival Cutoff Time

Open `AttendanceController.java` and find this line inside the `checkIn` method:

```java
LocalTime lateAfter = LocalTime.of(10, 0); // 10:00 AM
```

Change it to any time you want:
```java
LocalTime lateAfter = LocalTime.of(10, 15); // 10:15 AM
LocalTime lateAfter = LocalTime.of(9, 45);  // 9:45 AM
```

---

## 🗄️ Database Tables

### `employee`
| Column | Type | Description |
|---|---|---|
| id | BIGINT (PK) | Auto-generated ID |
| name | VARCHAR | Employee full name |
| email | VARCHAR | Employee email |
| department | VARCHAR | Department name |

### `attendance`
| Column | Type | Description |
|---|---|---|
| id | BIGINT (PK) | Auto-generated ID |
| employee_id | BIGINT | Reference to employee |
| employee_name | VARCHAR | Snapshot of employee name |
| date | DATE | Attendance date |
| check_in | TIME | Check-in timestamp |
| check_out | TIME | Check-out timestamp |
| status | VARCHAR | Present / Late / Absent |

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---



> ⭐ If this project helped you or inspired you, please give it a star on GitHub!
