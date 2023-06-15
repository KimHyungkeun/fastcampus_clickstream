package com.fastcampus.clickstream;

import java.time.Instant;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class ClickStreamAnalyzer {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment()
            .setParallelism(4);

        String bootstrapServers = "localhost:9092";
        KafkaSource<String> source = KafkaSource.<String>builder()
            .setBootstrapServers("localhost:9092")
            .setTopics("weblog")
            .setGroupId("test1")
            .setStartingOffsets(OffsetsInitializer.earliest())
            .setValueOnlyDeserializer(new SimpleStringSchema())
            .build();
        
        DataStream<String> dataStream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "KafkaSource");
        dataStream.map(new WebLogMapFunction());
    }

    public static class WebLogMapFunction implements MapFunction<String, WebLog> {

        @Override
        public WebLog map(String value) throws Exception {
            String[] tokens = value.split(" ");
            return new WebLog(tokens[0], Instant.parse(tokens[1]).toEpochMilli(), tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
        }

    }
}