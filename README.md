# RabbitMQ

Change the uri in the Connect class, for example amqp://guest:guest@your-server-fqdn:5672/

It's a RabbitMQ tester, all cases could be launched like this (windows) :

simple
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.simple.Send  "HelloWorld"
# launch 2 clients for testing
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.simple.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.simple.Receive 
 
work
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.work.Send  "1.2.3"
# launch 2 clients for testing
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.work.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.work.Receive 
 
pubsub
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.pubsub.Send  "publish 1"
# launch several clients for testing
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.pubsub.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.pubsub.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.pubsub.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.pubsub.Receive 

routing
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.routing.Send  "route me"
# launch several clients for testing
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.routing.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.routing.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.routing.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.routing.Receive 
 
topic
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.topic.Send  "topic publish"
 # launch several clients for testing
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.topic.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.topic.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.topic.Receive 
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.topic.Receive 
 
rpc
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.rpc.Send  
 java -cp D:/amqp-client-3.6.0.jar;target/RabbitMQ-0.0.1.jar ch.ebu.rabbitmq.rpc.Receive 
 
