# Recipes 

## About the Service

This is a project in Hyperskills course : Java Back-end Developer track.
This is a simple multi-user web service with Spring Boot that allows storing, retrieving, updating, and deleting recipes via RESTful API. And secured with basic HTTP authentication by Spring Boot Security. I used a PostgreSQL database to store the data in this project, you can change it to whatever database you like. If your database connection works properly, you can call some REST endpoints defined in ```localhost``` on **port 8081**. (see below)

Please note that this is a pure back-end project with little to no front-end. The service only allows you to send and receive Json-type data.
P/s : I am developing some new features including uploading and downloading pictures.
## How to Run 

* Clone this repository
* Change the dir to the root of the project
* Edit [application.properties](src/main/resources/application.properties) - set database info (db url, db driver, username, password, dialect)
* Edit [build.gradle] if you use a DBMS other than postgreSQL (need to add jdbc-driver to the dependencies block)
* Visit http://localhost:8081/ to check the connection and further endpoints. Recommend using PostMan to send requests.

## Allowed endpoints

### User registration
![image](https://user-images.githubusercontent.com/103060332/194550709-842d9404-eb25-4b09-8417-3773920a72d3.png)

```
POST /api/register

Accept: application/json
Content-Type: application/json

{
    "email" : "email@exampl.example",
    "password" : "example_password"
}
```

### User authentification


To unlock the remaining endpoints, you need to log in using http basic auth. Recommend using Postman !
![image](https://user-images.githubusercontent.com/103060332/194550845-10fe2358-0c34-43c1-b827-df9703c937de.png)


### Create a recipe
![image](https://user-images.githubusercontent.com/103060332/194550953-5b0ca866-1928-41b1-b6bc-0835eb8a7be1.png)

```
POST /api/recipes/

Accept: application/json
Content-Type: application/json

{
"name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

### Get all recipes sorted by update time

```
GET /api/recipes/
```

### Update a recipe
![image](https://user-images.githubusercontent.com/103060332/194551091-3a0336fd-b78c-4e00-a787-447ae1fc2b68.png)

```
PUT /api/recipes/{id}
Accept: application/json
Content-Type: application/json

{
   "name": "Warming Ginger Tea",
   "category": "beverage",
   "description": "Ginger tea is a warming drink for cool weather, ...",
   "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
   "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
}
```

### Delete a recipe by id

```
DELETE /api/recipes/{id}
```

### Retrieve a recipe by id

```
GET /api/recipes/{id}
```

