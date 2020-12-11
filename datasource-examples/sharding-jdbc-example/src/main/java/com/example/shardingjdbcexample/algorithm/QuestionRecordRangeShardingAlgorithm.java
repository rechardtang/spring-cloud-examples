package com.example.shardingjdbcexample.algorithm;

import com.example.shardingjdbcexample.utils.ShardingUtils;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;


import java.util.Collection;
import java.util.Date;

public class QuestionRecordRangeShardingAlgorithm implements RangeShardingAlgorithm<Date> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        return ShardingUtils.quarterRangeSharding(collection, rangeShardingValue);
    }
}
