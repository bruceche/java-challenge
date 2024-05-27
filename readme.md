### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.

### Feedback and Excitement
I'm thrilled about the opportunity to participate in this interview process. <br>
The tasks were both challenging and engaging, allowing me to showcase my skills effectively.<br>
Exploring Spring Boot for the first time has been truly invigorating, and I'm eager to delve deeper into this technology.<br> 
I'm genuinely enthusiastic about the possibility of joining your team and making meaningful contributions to your projects.<br> 
Thank you for considering my application?I look forward to the opportunity to work together.

### What I did

- Security Enhancements:
  - Added basic authentication and authorization for REST endpoints.

- Performance:
  - Added caching logic for database calls.
  - Enhanced employee retrieval performance by implementing pagination.

- Error Handling:
  - Implemented comprehensive error handling to improve the management of exceptions.
  - Created ErrorResponse object to standardize the format of error responses.
  - Added custom exception classes to handle specific errors.
  - Utilized @ControllerAdvice for centralized error handling.

- Code Improvements:
  - Modified EmployeeController to ensure CRUD operations work correctly.
  - Removed unnecessary setter method since Spring Boot's autowiring handles this automatically.
  - Modified the return types of controllers to ResponseEntity to improve the flexibility, clarity, and robustness of the API.
  - Refactored API calls and improved code readability by making appropriate changes.

- Database Enhancements:
  - Modified authentication to use JDBC.
  - Added sample data import DDL for easier setup.

- Documentation and comments:
  - Added comments and documentation to improve code readability.
  - Enhanced API documentation using SwaggerConfig.
  
- Miscellaneous:
  - Updated dependencies and configurations in pom.xml as necessary.


### What I would have done if I had more time

- Address the bugs that were introduced when adding security features:
  - Reauthentication of users when changing passwords is not performed during tests with Podman.
  - APIs can be called without authentication when tested with Swagger-UI.

- Add logging functionality and incorporate appropriate logging handling within the program.

- Configure environment variable files to set different environment parameters for various environments.

- Learn more about Spring Boot-related knowledge, and then try to apply it to this project.

### My experience in Java

- I have been working in Java-related projects since I started my career in 2009.
- However, since 2011, I have primarily focused on Gosu development due to my involvement in Guidewire ClaimCenter projects.
- Although I have occasionally worked with Java during this period, my primary expertise has been with Gosu.
- This is my first time using Spring Boot.