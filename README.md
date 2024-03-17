# Task Management

- First clone the repo using git clone https://github.com/mostafaelbeih1995/taskManagement.git
- Open the project in you IDEA and adjust configuration as the image attached.
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/6f7b6dcb-8ede-4075-9172-07b54f7e21e5)
- Now run the program
- Open localhost: http://localhost:8080/h2-console/ to view the database
- Enter the following credentials: username: sa, password: admin
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/b87bbfaa-55a0-4515-8cc1-fba55e21f1cc)
- You can use the attached postman collection "" to test the different endpoints of the system
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/267d54bb-63f5-4375-b5a8-b3059eac2b09)

- You can either authenticate or register a new user in order to get authorization key to access the system's endpoints
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/141b5f84-ec76-4a9a-8030-1bce70128c59)


## Improvements

- I am a little bit tight on time and I know there is a lot of modification and enhancements that needs to be done if I have more time including
    - Add Logging with meaningful messages that will clarify what is happening in the system in order to be able to debug on higher environments
    - Add DAO layer between Database and Endpoints with mapping between them using MapStruct for example
     - Add Api documentation so it's clear what are the endpoints the system exposes and what roles can access what endpoints
     - Add unit test to cover all test cases scenarios
     - Add Sending email functionality


