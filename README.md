# Veterinary Management System

Veterinary Management System is a RESTful API that helps manage the operations of a veterinary clinic. This API provides endpoints to manage various resources, including veterinary doctors, customers, animals, vaccines, and appointments.

## Main Features
* Managing veterinarians: saving, updating, viewing, and deleting 
* Managing the available days of doctors: saving, updating, viewing, and deleting 
* Managing customers: saving, updating, viewing, and deleting 
* Managing animals belonging to customers: saving, updating, viewing, and deleting 
* Managing vaccines applied to animals: saving, updating, viewing, and deleting 
* Creating appointments for animals to veterinarians: saving, updating, viewing, and deleting

## CRUD Constraints
* There can't be two animals with the same name and owner. (Unique constraint)
* Customers and doctors are unique based on their phone numbers and emails together. (Unique constraint)
* A new vaccine can't be created for an animal if that animal has an active protection with that vaccine. 
* Appointments are hourly. Appointment start times are always at the beginning of the hour. 
* Cascade delete is enabled for all entities where the remaining data would be irrelevant after deletion. (If a customer is deleted, all of their animals and appointments are deleted as well.)

## Images
*Entity Relationship Diagram*

<img width="600px" src="/media/DBMS%20ER%20diagram%20(UML%20notation).png" />

*Database Structure and Sample Data*

<img width="200px" src="/media/veterinarysql.png" />
<img width="500px" src="/media/customer.png" />
<img width="500px" src="/media/animal.png" />
<img width="500px" src="/media/vaccine.png" />
<img width="500px" src="/media/doctor.png" />
<img width="500px" src="/media/availableDate.png" />
<img width="500px" src="/media/appointment.png" />-

## API Endpoints

| Endpoint                                          | HTTP Method | Description                                                                 |
|---------------------------------------------------|:------------|-----------------------------------------------------------------------------|
| **customers**                                     |             |                                                                             |
| `/api/v1/customers/{id}`                          | GET         | Retrieves a customer with a specific ID.                                    |
| `/api/v1/customers/{id}`                          | PUT         | Updates a customer with a specific ID.                                      |
| `/api/v1/customers/{id}`                          | DELETE      | Deletes a customer with a specific ID.                                      |
| `/api/v1/customers`                               | GET         | Retrieves customers.                                                        |
| `/api/v1/customers`                               | POST        | Creates a new customer.                                                     |
| `/api/v1/customers/find-by-name`                  | GET         | Filters customers by their names.                                           |
| **animals**                                       |             |                                                                             |
| `/api/v1/animals/{id}`                            | GET         | Retrieves an animal with a specific ID.                                     |
| `/api/v1/animals/{id}`                            | PUT         | Updates an animal with a specific ID.                                       |
| `/api/v1/animals/{id}`                            | DELETE      | Deletes an animal with a specific ID.                                       |
| `/api/v1/animals`                                 | GET         | Retrieves animals.                                                          |
| `/api/v1/animals`                                 | POST        | Creates a new animal.                                                       |
| `/api/v1/animals/find-by-name`                    | GET         | Filters animals by their names.                                             |
| `/api/v1/animals/find-by-customer`                | GET         | Retrieves animals of a customer with a specific ID.                         |
| **vaccines**                                      |             |                                                                             |
| `/api/v1/vaccines/{id}`                           | GET         | Retrieves a vaccine with a specific ID.                                     |
| `/api/v1/vaccines/{id}`                           | PUT         | Updates a vaccine with a specific ID.                                       |
| `/api/v1/vaccines/{id}`                           | DELETE      | Deletes a vaccine with a specific ID.                                       |
| `/api/v1/vaccines`                                | GET         | Retrieves vaccines.                                                         |
| `/api/v1/vaccines`                                | POST        | Creates a new vaccine.                                                      |
| `/api/v1/vaccines//find-by-protection`            | GET         | Retrieves vaccine protection dates within a specified date range.           |
| `/api/v1/vaccines//find-by-animal`                | GET         | Retrieves vaccines applied to an animal with a specific ID.                 |
| **doctors**                                       |             |                                                                             |
| `/api/v1/doctors/{id}`                            | GET         | Retrieves a doctor with a specific ID.                                      |
| `/api/v1/doctors/{id}`                            | PUT         | Updates a doctor with a specific ID.                                        |
| `/api/v1/doctors/{id}`                            | DELETE      | Deletes a doctor with a specific ID.                                        |
| `/api/v1/doctors`                                 | GET         | Retrieves doctors.                                                          |
| `/api/v1/doctors`                                 | POST        | Creates a new doctor.                                                       |
| **available_dates**                               |             |                                                                             |
| `/api/v1/available_dates/{id}`                    | GET         | Retrieves an available date with a specific ID.                             |
| `/api/v1/available_dates/{id}`                    | PUT         | Updates an available date with a specific ID.                               |
| `/api/v1/available_dates/{id}`                    | DELETE      | Deletes an available date with a specific ID.                               |
| `/api/v1/available_dates`                         | GET         | Retrieves available dates.                                                  |
| `/api/v1/available_dates`                         | POST        | Creates a new available date.                                               |
| **appointments**                                  |             |                                                                             |
| `/api/v1/appointments/{id}`                       | GET         | Retrieves an appointment with a specific ID.                                |
| `/api/v1/appointments/{id}`                       | PUT         | Updates an appointment with a specific ID.                                  |
| `/api/v1/appointments/{id}`                       | DELETE      | Deletes an appointment with a specific ID.                                  |
| `/api/v1/appointments`                            | GET         | Retrieves appointments.                                                     |
| `/api/v1/appointments`                            | POST        | Creates an appointment for an animal.                                       |
| `/api/v1/appointments/find-by-doctor-appointment` | GET         | Retrieves appointments for a specific doctor within a specified date range. |
| `/api/v1/appointments/find-by-animal-appointment` | GET         | Retrieves appointments for a specific animal within a specified date range. |

## Installation
1. Clone the repository.
2. Inside the project, create the Mysql database for using the veterinary.sql file.
3. Open the src/main/resources/application.properties file and update the database connection information.
4. Start the project from your IDE.
5. Go to http://localhost:8080/swagger-ui/index.html#/ from your web browser.
6. Use the requests in the Swagger to test the API.