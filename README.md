# Student Management System 

## Mission
The Student Management System (SMS) is a REST API designed to help schools efficiently manage student admissions, courses, and enrollments. This API allows administrators to manage student data, course assignments, and student profiles. It also provides students with the ability to update their profile, search for courses, and enroll in them.


### Admin Operations
1. **Student Admission**:
   - Add student details like Name, Date of Birth, Gender, Unique Student Code.
   - Add multiple addresses (Permanent, Correspondence, Current).
2. **Course Management**:
   - Add course details such as Course Name, Description, Course Type, Duration, Topics.
3. **Course Assignment**:
   - Assign courses to students.
4. **Search Functionality**:
   - Get students by their name.
   - Get students assigned to a particular course.

### Student Operations
1. **Profile Management**:
   - Students can update Email, Mobile Number, Parents' Names, and Address.
2. **Course Search & Enrollment**:
   - Students can search for topics and courses they are assigned to.
   - Students can leave a course.

## Technologies and Versions Used
- **Spring Boot**: For REST API development.
- **JPA with Hibernate**: For database interactions.
- **Spring Security**: For authentication and authorization.
- **Swagger (Optional)**: For API documentation.
- **JUnit**: For unit testing.
- **Postman**: For testing API endpoints.
- Spring Boot: 3.4.4.
- Maven 4.0.0.
- Java: 21.
- MySQL: latest version compatible with the project.
- Lombok: latest version compatible.
- JSON Web Tokens (JWT): 0.12.5.
