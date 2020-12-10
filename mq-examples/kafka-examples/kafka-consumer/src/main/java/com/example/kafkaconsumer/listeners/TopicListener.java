package com.example.kafkaconsumer.listeners;

import com.example.kafkaconsumer.pojo.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TopicListener {


    @KafkaListener(topics = "topic1", groupId = "foo")
    public void singleTopicListener(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

//    @KafkaListener(topics = "topic1, topic2", groupId = "foo")
//    public void multiTopicsListener(String message) {
//        System.out.println("Received Message in group foo: " + message);
//    }
//
//    @KafkaListener(topics = "topic1")
//    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        System.out.println("Received Message: " + message + "from partition:" + partition);
//    }

//    @KafkaListener(
//            topicPartitions = @TopicPartition(topic = "topic1", partitionOffsets = {
//                    @PartitionOffset(partition = "0", initialOffset = "0"),
//                    @PartitionOffset(partition = "3", initialOffset = "0")
//            }),
//            containerFactory = "partitionsKafkaListenerContainerFactory")
//    public void listenToPartition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        System.out.println("Received Message: " + message + "from partition: " + partition);
//    }

//    @KafkaListener(
//            topics = "topic1",
//            containerFactory = "filterKafkaListenerContainerFactory")
//    public void listenWithFilter(String message) {
//        System.out.println("Received Message in filtered listener: " + message);
//    }
//
//    @KafkaListener(
//            topics = "topic1",
//            containerFactory = "jsonKafkaListenerContainerFactory")
//    public void greetingListener(Greeting greeting) {
//        // process greeting message
//    }
}
