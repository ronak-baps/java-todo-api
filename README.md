
maven = Maven is a popular open-source build tool developed by the Apache Group to build, publish, and deploy several projects at once for better project management.

maven commadns

** it's mandatory to install maven in your system if you want to use mvn commands.

- mvn spring-boot:run
# Runs the project 

- java -jar target/first-0.0.1-SNAPSHOT.jar
# You can also start project using this command but before you have to run this mvn package command.

- mvn validate
# Validates the project, checking if all necessary information is available.

- mvn compile
# Compiles the project’s source code.

- mvn test
# Runs the tests using a suitable unit testing framework, such as JUnit.

- mvn package
# Packages the compiled code into a distributable format, such as a JAR.

- mvn verify
# Runs any checks to verify the package is valid and meets quality criteria.

- mvn install
# Installs the package into the local repository, making it available for use as a dependency in other projects.

- mvn deploy
# Copies the final package to the remote repository for sharing with other developers and projects.

- mvn clean
# Delete the target dir.


-----------------------

IOC Container = Inversion Of Control

Use of IOC -> when you want to create any instance/object without spring we have to define like this <Cname> obj = new <Cname>; but in spring they automatically gives the instace/object using IOC. means we don't have to create instace/object manually.

Aplication Context = Aplication Context is a way to implement IOC Container.



@<name> -> called annotation

@Component => when IOC container scan classes, they find @Component annotation and if the class have this annotation IOC stored this class object.
- what componect do is that class is automattically registered as a spring bean.
- now what is bean? -> bean is nothing just an that clss object.


// It's called dependency injection
    @Autowired
    private Dog dog;

# dependency injection means we call/create other file class object with spring @autowired annotation. no need to write <class> obj = new <class>;


NOTE :: you have to create a component inside this dir src/main/java/<projectName>. if you are made any component outside this directory spring not able to scan.



/////////////////////////////////////////////////////////

MongoDB Commands

- mongosh
# going to mongodb shell


- show dbs
# give the list of DBs

- use school
# if schoold db not exsist mongodb create the db and use the db also.

- show collections
# shows the table of selected db

- e.x :: db.students.insertOne({"name": "ronak", "age": 21})
# if studnets collection not exist mongodb create one and inset these record.

- db.students.find().pretty() <- pretty is optional
# shows the all record of students collections.

- db.students.find({"name": "ronak"})
# find the specific record in students collection.

- db.students.deleteOne({"name": "ronak"})
# delete the specific record in studnts collection.

- db.students.deleteMany({})
# delete all records in students collection.
