# Introduction of the application

### Description

Name of the project is SampleProject. I have written the SampleProject like example of my code as I used to write it.
I had not any specific topic and also the project is just a small mix of knowledge in this area.

There is designed how to process the transactions. The hosts and terminals are just some blind simulator to approve anything.
Rabbit MQ is used for backup of transaction data to some deposit for example (I have just look for some usage of the technology).
The application is build by docker compose file into your local docker. In the end the containers are started for the usage.

You can try to perform transaction by call REST API:<br/>
"**POST ht<span>tp://localhost:8080/start?transactionAmount=1000**"

### Used technologies

* Spring Boot
* Java 17 Temurin
* REST API
* Rabbit MQ
* PostgreSQL DB
* Docker
* Test-containers

### Access

#### RABBIT MQ
Url: ht<span>tp://localhost:15672/
<br/>Username: guest
<br/>Password: guest

#### PostgreSQL
Url: jdbc:postgresql://localhost:2346/sample
<br/>Username: postresuser
<br/>Password: password