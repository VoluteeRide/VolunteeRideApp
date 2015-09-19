## VolunteeRideApp-svc
This Repo tracks the server side code base for the services related to VolunteeRide App.

### Design Collaboration:
* [Design Document](https://drive.google.com/folderview?id=0B_O4LeO9jYKLfm95MjlQeHpBbHZJVGFxM3ZyTEdyamZCMDk2aFplbE16S3IydzRyTm82cHc&usp=sharing)
* [Requirements Document](https://drive.google.com/drive/folders/0B_O4LeO9jYKLfm5VMzVjcTdkQXQyeDY4WjFyUHpKeHRCVi04Wk8wUVEtSG1IemEyTGIzUlk)

### Steps to setup Project on local system.
1. Pre-Requsites
  * [Dowload Intellij](https://www.jetbrains.com/idea/download/)
  * [Download Mongo DB](https://www.mongodb.org/downloads)
  * [Set up MongoDb](http://docs.mongodb.org/manual/installation/)
  
2. Clone the project on the local system. 
3. Import the project as a gradle project in Intellij.
3. Start MongoDB server using command **mongod --dbPath <PATH_TO_YOUR_DB_DIRECTORY>** by opening a new shell.
4. To quickly insert sample data in the test database, execute com.volunteeride.dao.RideDAOTest#test save ride test by having  
   @Rollback(false) annotation in the following way. The annotation will make sure that the data gets committed to the database.
   ```
   @Rollback(false)
    def "test save ride "() {

        setup: "Set up ride object to insert"
        ...
        ...
    }
    ```
5. In order to confirm if the data was inserted in the mongodb databse, open a new shell, type command **mongo**, a mongo shell    will open. Type the following commands to find the inserted data.
   * **show dbs** .. will show available databases. You should see volunteerideTest database.
   * **use volunteerideTest** .. command to use this db
   * **show collections** .. will show ride, center and user collections.
   * **db.ride.find()** .. will display data in ride collection. Copy the center id for one of the ride document for testing 
     rest webservice in later steps.

6. Open mongodbConfig.properties and change the value for **mongodb.db** to **volunteerideTest**
7. In the gradle Tasks, in intellij, run **bootRun** Task. This will deploy the app on an embedded tomacat server.
8. Go to the browser of your choice or any rest client plugins for these browsers (RestClient plugin for Mozilla)
9. In-order to test whether are setup is successfull, we will make a rest call to one of the sample rest-webservices created for    the project which is ***Retrieve All Rides for a particular center.*** .
10. Type url http://localhost:8080/volunteeride/centers/{PLUGIN THE CENTER ID COPIED EARLIER}/rides
10. You should get a Json response for the find rides rest webservice.
11. ***CONGRATUALTIONS YOU HAVE SUCCESSFULLY SETUP THE VOLUNTEERIDE APP IN YOUR LOCAL SYSTEM :) !!!***
   
   
  
