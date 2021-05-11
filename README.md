# cab

Car Booking System

Setup
-----

* To run this project as a standalone jar, follow these steps.
* Clone repository.

        git clone git@github.com:dashrath1526/cab.git


Command-line Instructions
-------------------------

* **Prerequisites:**
    * Install [Java](https://java.com) and [Maven](https://maven.apache.org/download.html).
    * I have used Java 1.8 and Maven 3.5.4
    * You may need to set your `JAVA_HOME` and `MAVEN_HOME`.

```bash
cd cab
# clean and build jar
mvn clean package
java -jar target/cab-0.0.1-SNAPSHOT-shaded.jar 
```

# API endpoints

These endpoints allow you to handle user regisration, find cars in KM radius, block a car, and release a car.

### GET http://localhost:8080/cab/webapi/user/user1@email.com
Get details of registered user by email_id.

**Parameters**
`email_id` - required: string - Registered user email id.                                                                     |

**Response**

```
{
    "balance": 49770,
    "email": "user1@email.com",
    "id": 1,
    "location": {
        "id": 3,
        "latitude": 22.708617,
        "longitude": 73.11564,
        "name": "Anand"
    },
    "name": "User One",
    "password": "user1"
}
```
