package com.example.shardingjdbcexample.utils;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ShardingUtils {

    public static final String QUARTER_SHARDING_PATTERN = "%s_%d_q%d";

    /**
     * logicTableName_{year}_q{quarter}
     * 按季度范围分片
     *
     * @param availableTargetNames 可用的真实表集合
     * @param shardingValue        分片值
     * @return
     */
    public static Collection<String> quarterRangeSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Range<Date> valueRange = shardingValue.getValueRange();
        Date lower = valueRange.lowerEndpoint();
        Date upper = valueRange.upperEndpoint();
        LocalDate lowerLocalDate =
                lower.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate upperLocalDate =
                upper.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int lowerYear = lowerLocalDate.getYear();
        int lowerMonth = lowerLocalDate.getMonthValue();
        int lowerQuarter = getQuarter(lowerMonth);

        int upperYear = upperLocalDate.getYear();
        int upperMonth = upperLocalDate.getMonthValue();
        int upperQuarter = getQuarter(upperMonth);

        String logicTableName = shardingValue.getLogicTableName();
        List<String> actualTableNames = new ArrayList<>();

        if (lowerYear == upperYear) {
            // 不跨年
            for (int quarter = lowerQuarter; quarter <= upperQuarter; quarter++) {
                String actualTableName = String.format(QUARTER_SHARDING_PATTERN, logicTableName, lowerYear, quarter);
                actualTableNames.add(actualTableName);
            }
        } else {
            // 跨年
            for (int quarter = lowerQuarter; quarter <= Constant.INT_4; quarter++) {
                String actualTableName = String.format(QUARTER_SHARDING_PATTERN, logicTableName, lowerYear, quarter);
                actualTableNames.add(actualTableName);
            }

            for (int year = lowerYear + 1; year < upperYear; year++) {
                for (int quarter = 1; quarter <= Constant.INT_4; quarter++) {
                    String actualTableName = String.format(QUARTER_SHARDING_PATTERN, logicTableName, year, quarter);
                    actualTableNames.add(actualTableName);
                }
            }

            for (int quarter = 1; quarter <= upperQuarter; quarter++) {
                String actualTableName = String.format(QUARTER_SHARDING_PATTERN, logicTableName, upperYear, quarter);
                actualTableNames.add(actualTableName);
            }

        }

        if (!availableTargetNames.containsAll(actualTableNames)) {
            throw new IllegalArgumentException("分片异常！shardingValue=" + shardingValue + "; availableTargetNames=" + availableTargetNames);
        }
        return actualTableNames;
    }

    /**
     * logicTableName_{year}_q{quarter}
     * 按季度精确分片
     *
     * @param availableTargetNames 可用的真实表集合
     * @param shardingValue        分片值
     * @return
     */
    public static String quarterPreciseSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Date value = shardingValue.getValue();
        LocalDate localDate =
                value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int quarter = getQuarter(month);
        String actualTableName = String.format(QUARTER_SHARDING_PATTERN, logicTableName, year, quarter);
        if (availableTargetNames.contains(actualTableName)) {
            return actualTableName;
        }
        throw new IllegalArgumentException("分片异常！shardingValue=" + shardingValue + "; availableTargetNames=" + availableTargetNames);
    }

    private static int getQuarter(int month) {
        if (month >= Constant.INT_1 && month <= Constant.INT_3) {
            return Constant.INT_1;
        } else if (month >= Constant.INT_4 && month <= Constant.INT_6) {
            return Constant.INT_2;
        } else if (month >= Constant.INT_7 && month <= Constant.INT_9) {
            return Constant.INT_3;
        } else if (month >= Constant.INT_10 && month <= Constant.INT_12) {
            return Constant.INT_4;
        } else {
            throw new IllegalArgumentException("month非法！month=" + month);
        }
    }

    static interface Constant {
        int INT_1 = 0;
        int INT_2 = 0;
        int INT_3 = 0;
        int INT_4 = 0;
        int INT_5 = 0;
        int INT_6 = 0;
        int INT_7 = 0;
        int INT_8 = 0;
        int INT_9 = 0;
        int INT_10 = 0;
        int INT_11 = 0;
        int INT_12 = 0;
    }
}
