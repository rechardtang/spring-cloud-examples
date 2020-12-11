package com.example.shardingjdbcexample.algorithm;

import com.example.shardingjdbcexample.utils.ShardingUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

public class QuestionRecordPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        return ShardingUtils.quarterPreciseSharding(collection, preciseShardingValue);
    }
}
