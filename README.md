# SAAP-EDA-Kafka
Code and Paper for my Software Architecture and Patterns Topic Event-Driven Architecture with Apache Kafka

## Paper
### Abstract
The Event-Driven Architecture and Apache Kafka play an important role in the software development industry.
Using them together leads to extremely flexible and highly scalable software architectures that are able to process massive loads of data with an incredible efficiency.
Especially regarding Big Data and Internet of Things the Event-Driven Architecture tends to become a state-of-the-art pattern.
Furthermore, the use of the Microservice pattern enhances the Event-Driven Architecture leading to fully decoupled and highly coherent services.
In addition, using the Apache Kafka Message broker ensures mechanisms to achieve high performance, fault tolerance and much more.

This paper will explain the key concepts of both technologies and show an example application, which demonstrates how good Apache Kafka and Event-Driven Architectures work together.
The main focus of this paper is providing the necessary information that the readers of this paper are able to design an application using the Event-Driven Architecture and Apache Kafka.
Furthermore, the readers should understand why and when to use this technology stack and for which situations it is more appropriate to use an alternative architecture or and alternative messaging system.
Moreover, the example project should provide a good starting point for all kinds of applications.
The use-cases provided in the Related Work section should inspire the readers to adapt this sample architecture depending on the specific requirements of their applications.

### References
- [Paper](./Paper/Event-Driven-Architecture-with-Apache-Kafka.pdf)

## Project
To start the project install Kafka start an instance and create the needed topic.
Then run the simulator, the services and required additional services.

### Setup Kafka 
***Install Kafka and ZooKeeper***:  
```
brew install zookeeper
brew install kafka
```

***Start ZooKeeper***:  
```
zkServer start
```

***Start Kafka***:  
```
kafka-server-start Kafka/server.properties
```

***Create topic***:  
```
kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 2 --topic EquipmentStatus
```

### Run EdgeDevice Simulator
Configure the Parameters inside the Main.java of the EdgeDeviceSimulator, or just run it with the preconfigured configurations.
Run the Simulator with the commands:
```
cd EdgeDeviceSimulator
./gradlew run
```

### Run Timescale Service
First start a docker container with timescaledb:
```
docker run -d --name timescale-db -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres timescale/timescaledb:latest-pg13
```

Then start the TimescaleService with:
```
cd TimescaleService
./gradlew run
```
The TimescaleService creates the database table using Flyway Migration, so nothing more has to be done.

Start a Grafana container, [connect Grafana to the database](https://docs.timescale.com/timescaledb/latest/tutorials/grafana/installation/) and configure your dashboards:
```
docker run -d -p 3000:3000 --name=grafana -e "GF_INSTALL_PLUGINS=grafana-clock-panel,grafana-simple-json-datasource" grafana/grafana
```

### Run Telegram Monitoring Service
Create a [Telegram Bot Token](https://sendpulse.com/knowledge-base/chatbot/telegram/create-telegram-chatbot).
Paste the token into getBotToken() of the EquipmentStatusBot class. (Don't try the current one it is revoked)
Then run the Service using: 
```
cd TelegramService
./gradlew run
```

Communicate with the ChatBot...
