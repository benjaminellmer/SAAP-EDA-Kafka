# SAAP-EDA-Kafka
Code and Paper for my Software Architecture and Patterns Topic Event-Driven Architecture with Apache Kafka

## Paper
Coming soon...

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
Paste the token into getBotToken() of the EquipmentStatusBot class.
Then run the Service using: 
```
cd TelegramService
./gradlew run
```

Communicate with the ChatBot...
