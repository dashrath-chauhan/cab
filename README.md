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

These endpoints allow you to handle user regisration, find cars in 1KM radius, block a car, and release a car.

### /user/register
`Description: User registration.`

**Request**<br/>
`ContentType: application/json`<br/>
`Request object: User Entity`<br/>
```
POST http://localhost:8080/cab/webapi/user/register
```
```
{
    "balance": 2000,
    "email": "user2@email.com",
    "location": {
        "name": "Bhuj"
    },
    "name": "User Two",
    "password": "user2"
}
```

**Response**<br/>
`ContentType: application/json`<br/>
`Response object: User Entity`<br/>

```
{
    "balance": 20000,
    "email": "user2@email.com",
    "id": 2,
    "location": {
        "id": 5,
        "latitude": 22.701072,
        "longitude": 73.111302,
        "name": "Bhuj"
    },
    "name": "User Two",
    "password": "user2"
}
```
### /user/{email_id}
`Description: Registered user email id.`
**Request**<br/>
`Type: String`
```
http://localhost:8080/cab/webapi/user/user1@email.com
```
**Response**<br/>
`ContentType: application/json`<br/>
`Response object: User Entity`<br/>
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
`Description: Find all the available cars for user within 1KM radius.`
**Request**<br/>
`ContentType: application/json`<br/>
`Request object: User Entity - Required. However all the parameters in object are not mandatory.`<br/>
`Mandatory params in object: email`<br/>
```
http://localhost:8080/cab/webapi/car/locate-cars
```
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
or
```
{
    "email": "user1@email.com"
}
```
**Response**<br/>
`ContentType: application/json`<br/>
`Response object: Array of car entities.`<br/>
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

### /transact/block-car
`Description: This api can be used after using locate cars api to select car and block that for particular user.`

**Request**<br/>
`ContentType: application/json`<br/>
`Request object: Block Info object - Wraps User entity and Car entity as single object.`<br/>
`Mandatory params in object: User: email, destination. Car: id`

```
POST http://localhost:8080/cab/webapi/transact/block-car
```
```
{ 
    "user": {
        "balance": 50000,
        "email": "user1@email.com",
        "id": 1,
        "location": {
            "id": 1,
            "latitude": 22.698637,
            "longitude": 73.115573,
            "name": "Ahmedabad"
        },
        "name": "User One",
        "password": "user1",
        "destination": "Anand"
    }, 
    "car": {
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
    }
}
```
or
```
{ 
    "user": {
        "email": "user1@email.com",
        "destination": "Anand"
    }, 
    "car": {
        "id": 7
    }
}
```

**Response**<br/>
`ContentType: application/json`<br/>
`Response object: Transaction Entity`<br/>

```
{
    "car": {
        "available": false,
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
    "destination": {
        "id": 3,
        "latitude": 22.708617,
        "longitude": 73.11564,
        "name": "Anand"
    },
    "id": 4,
    "paid": false,
    "startTime": "11:05:2021 13:44:25",
    "user": {
        "balance": 50000,
        "email": "user1@email.com",
        "id": 1,
        "location": {
            "id": 1,
            "latitude": 22.698637,
            "longitude": 73.115573,
            "name": "Ahmedabad"
        },
        "name": "User One",
        "password": "user1"
    }
}
```

### /transact/user-transaction
`Description: This api find the active transaction(not paid) of user since one user can block one car at a time in real scenario this will always fetch single result.`

**Request**<br/>
`ContentType: application/json`<br/>
`Request object: User entity objecj.`<br/>
`Mandatory params in object: email`<br/>

```
POST http://localhost:8080/cab/webapi/transact/user-transaction
```
```
{
    "balance": 50000,
    "email": "user1@email.com",
    "id": 1,
    "location": {
        "id": 1,
        "latitude": 22.698637,
        "longitude": 73.115573,
        "name": "Ahmedabad"
    },
    "name": "User One",
    "password": "user1"
}
```
or
```
{
    "email": "user1@email.com",
}
```

**Response**<br/>
`ContentType: application/json`<br/>
`Response object: Transaction Entity`
```
{
    "car": {
        "available": false,
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
    "destination": {
        "id": 3,
        "latitude": 22.708617,
        "longitude": 73.11564,
        "name": "Anand"
    },
    "id": 4,
    "paid": false,
    "startTime": "11:05:2021 13:44:25",
    "user": {
        "balance": 50000,
        "email": "user1@email.com",
        "id": 1,
        "location": {
            "id": 1,
            "latitude": 22.698637,
            "longitude": 73.115573,
            "name": "Ahmedabad"
        },
        "name": "User One",
        "password": "user1"
    }
}
```

### /transact/user-transaction
`Description: This api find the active transaction of user and ends it. There are multiple operation in this api.`<br/>
`1. Find active transaction of user.`<br/>
`2. Calculate time difference in minute. Since I have defined rate calucation for trip per minute.`<br/>
`3. Calculte and pay rent from users balance.`<br/>
`4. Release and make car available for booking, change the location of car and user to new location. Since at the end of the trip both user and car will be at the destination which user had selected.`<br/>

**Request**<br/>
`ContentType: application/json`<br/>
`Request object: User entity object.`<br/>
`Mandatory params in object: email in User object.`<br/>

```
POST http://localhost:8080/cab/webapi/transact/user-transaction
```
```
{
    "balance": 50000,
    "email": "user1@email.com",
    "id": 1,
    "location": {
        "id": 1,
        "latitude": 22.698637,
        "longitude": 73.115573,
        "name": "Ahmedabad"
    },
    "name": "User One",
    "password": "user1"
}
```
or
```
{
    "email": "user1@email.com",
}
```

**Response**<br/>
`ContentType: application/json`<br/>
`Response object: Transaction Entity`<br/>

```
{
    "car": {
        "available": true,
        "id": 7,
        "location": {
            "id": 3,
            "latitude": 22.708617,
            "longitude": 73.11564,
            "name": "Anand"
        },
        "model": "A1",
        "name": "Audi",
        "rentPerMin": 230
    },
    "destination": {
        "id": 3,
        "latitude": 22.708617,
        "longitude": 73.11564,
        "name": "Anand"
    },
    "endTime": "11:05:2021 13:55:53",
    "id": 1,
    "paid": true,
    "startTime": "11:05:2021 13:49:10",
    "user": {
        "balance": 48620,
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
}
```
