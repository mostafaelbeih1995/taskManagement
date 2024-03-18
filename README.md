# Task Management

## Running the system

There are two ways to run the system

### Cloning the repo


- First clone the repo using git clone https://github.com/mostafaelbeih1995/taskManagement.git
- Open the project in you IDEA and adjust configuration as the image attached.
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/6f7b6dcb-8ede-4075-9172-07b54f7e21e5)

- Now run the program

### Running Docker image

- I have zipped docker image where you can run a docker container and test the endpoints, I can share it via private link if needed
  ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/1b1b911a-03d1-4e8b-ad81-3a3183177c47)

- Unzip the file and run the following command : <span style="color:red">**gunzip -c ‘task-management-001.tar.gz’ | docker load**</span>
- Run the image using the following command: **docker run --name task-management -d -p 8080:8080 task-management:0.0.1-SNAPTSHOT**


## Testing the project

- Open localhost: http://localhost:8080/h2-console/ to view the database
- Enter the following credentials: username: sa, password: admin, database path : jdbc:h2:mem:testdb
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/b87bbfaa-55a0-4515-8cc1-fba55e21f1cc)

- You can use the attached postman collection "" to test the different endpoints of the system
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/267d54bb-63f5-4375-b5a8-b3059eac2b09)

- You can either authenticate or register a new user in order to get authorization key to access the system's endpoints
 ![image](https://github.com/mostafaelbeih1995/taskManagement/assets/26060891/141b5f84-ec76-4a9a-8030-1bce70128c59)

## Improvements

- I am a little bit tight on time and I know there is a lot of modification and enhancements that needs to be done if I have more time including
     - Add Logging with meaningful messages that will clarify what is happening in the system in order to be able to debug on higher environments
     - Enhance Validation to cover all failures in the system with custom messages
     - Add DAO layer between Database and Endpoints with mapping between them using MapStruct for example
     - Add Api documentation so it's clear what are the endpoints the system exposes and what roles can access what endpoints
     - Add unit test to cover all test cases scenarios
     - Add Sending email functionality
     - Dockerizing the microservice
     - These are just a part of the improvments that can be done, there is much more I believe


