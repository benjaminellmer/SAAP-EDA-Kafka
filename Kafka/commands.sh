# Install zookeeper
brew install zookeeper

# Install kafka
brew install kafka

# Start zkeeper server
zkServer start

# Start kafka server
kafka-server-start server.properties

# Create Topic
kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 2 --topic EquipmentStatus
