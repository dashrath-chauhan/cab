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

### /user/{email_id}
`Description:` `Registered user email id.`

**Request**
`Type:` `String`

```
http://localhost:8080/cab/webapi/user/user1@email.com
```

**Response**
`Type:` `User Entity`

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

### /car/locate-cars
`Description:` `Find all the available cars for user within 1KM radius.`

**Request**
`Type:` `JSON: Required. However all the parameters in user object arent mandatory. Only email in the request object will also suffice.`
```
http://localhost:8080/cab/webapi/car/locate-cars
```
With entire request object
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
or with email id only
{
    "email": "user1@email.com",
}

```
**Response**
`Type:` `Array of car entities.`
```
[
    {
        "available": true,
        "id": 1,
        "location": {
            "id": 1,
            "latitude": 22.698637,
            "longitude": 73.115573,
            "name": "Ahmedabad"
        },
        "model": "Civic",
        "name": "Honda",
        "rentPerMin": 80
    },
    {
        "available": true,
        "id": 5,
        "location": {
            "id": 5,
            "latitude": 22.701072,
            "longitude": 73.111302,
            "name": "Bhuj"
        },
        "model": "CLA",
        "name": "Mercedez-Benz",
        "rentPerMin": 220
    },
    {
        "available": true,
        "id": 7,
        "location": {
            "id": 7,
            "latitude": 22.703652,
            "longitude": 73.115334,
            "name": "Rajkot"
        },
        "model": "A1",
        "name": "Audi",
        "rentPerMin": 230
    },
    {
        "available": true,
        "id": 8,
        "location": {
            "id": 8,
            "latitude": 22.695707,
            "longitude": 73.119411,
            "name": "Umreth"
        },
        "model": "A1",
        "name": "Audi",
        "rentPerMin": 260
    }
]
```

