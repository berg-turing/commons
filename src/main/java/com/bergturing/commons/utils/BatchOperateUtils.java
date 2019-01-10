package com.bergturing.commons.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * 分批处理工具类
 *
 * @author bo.he02@hand-china.com
 * @apiNote 2019/1/7
 */
public class BatchOperateUtils {
    /**
     * 日志打印对象
     */
    private static Logger logger = LoggerFactory.getLogger(BatchOperateUtils.class);

    /**
     * 默认批处理的条数
     */
    public static int DEFAULT_BATCH_COUNT_1000 = 1000;

    /**
     * 【无批次标号】按照 batchCount 数量分批处理数据
     *
     * @param batchDataList  批次数据
     * @param actionFunction 逻辑处理对象
     * @param resultFunction 结果处理对象，如果没有结果处理对象，则返回最后一个批次处理数据的结果
     * @param <T>            批次数据类型
     * @return 批量处理数据的结果
     */
    public static <T, R> R batchOperate(List<T> batchDataList, Function<List<T>, R> actionFunction, BinaryOperator<R> resultFunction) {
        return BatchOperateUtils.batchOperate(batchDataList, DEFAULT_BATCH_COUNT_1000, actionFunction, resultFunction);
    }

    /**
     * 【无批次标号】按照 batchCount 数量分批处理数据
     *
     * @param batchDataList  批次数据
     * @param batchCount     每一批处理的数据量
     * @param actionFunction 逻辑处理对象
     * @param resultFunction 结果处理对象，如果没有结果处理对象，则返回最后一个批次处理数据的结果
     * @param <T>            批次数据类型
     * @return 批量处理数据的结果
     */
    public static <T, R> R batchOperate(List<T> batchDataList, int batchCount, Function<List<T>, R> actionFunction, BinaryOperator<R> resultFunction) {
        //调用没有下标的处理方法
        return BatchOperateUtils.batchOperate(batchDataList, batchCount, actionFunction, null, resultFunction);
    }


    /**
     * 【有批次标号】按照 batchCount 数量分批处理数据
     *
     * @param batchDataList    批次数据
     * @param biActionFunction 带标号的逻辑处理对象
     * @param resultFunction   结果处理对象，如果没有结果处理对象，则返回最后一个批次处理数据的结果
     * @param <T>              批次数据类型
     * @return 批量处理数据的结果
     */
    public static <T, R> R batchOperate(List<T> batchDataList, BiFunction<Integer, List<T>, R> biActionFunction, BinaryOperator<R> resultFunction) {
        return BatchOperateUtils.batchOperate(batchDataList, DEFAULT_BATCH_COUNT_1000, biActionFunction, resultFunction);
    }

    /**
     * 【有批次标号】按照 batchCount 数量分批处理数据
     *
     * @param batchDataList    批次数据
     * @param batchCount       每一批处理的数据量， 默认为 BATCH_MAX_COUNT
     * @param biActionFunction 带标号的逻辑处理对象
     * @param resultFunction   结果处理对象，如果没有结果处理对象，则返回最后一个批次处理数据的结果
     * @param <T>              批次数据类型
     * @return 批量处理数据的结果
     */
    public static <T, R> R batchOperate(List<T> batchDataList, int batchCount, BiFunction<Integer, List<T>, R> biActionFunction, BinaryOperator<R> resultFunction) {
        //调用有下标的处理方法
        return BatchOperateUtils.batchOperate(batchDataList, batchCount, null, biActionFunction, resultFunction);
    }

    private static <T, R> R batchOperate(List<T> batchDataList, int batchCount, Function<List<T>, R> actionFunction,
                                         BiFunction<Integer, List<T>, R> biActionFunction, BinaryOperator<R> resultFunction) {
        if (null == actionFunction && null == biActionFunction) {
            throw new IllegalArgumentException("两个逻辑对象不能同时为空");
        }
        if (actionFunction != null && biActionFunction != null) {
            throw new IllegalArgumentException("两个逻辑对象不能同时存在");
        }

        //处理数据的结果
        R result = null;

        //批次处理开始时间
        LocalDateTime start = null;
        //结束时间
        LocalDateTime end = null;

        if (logger.isDebugEnabled()) {
            start = LocalDateTime.now();
            logger.debug("当前批次开始处理... 开始时间 {}", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS")));
        }

        //判断数据是否存在
        if (CollectionUtils.isNotEmpty(batchDataList)) {
            //处理的数据的数量
            int size = batchDataList.size();
            //下标从
            int fromIndex = 0;
            //下标至
            int toIndex = 0;

            //每一个批次处理的数据量
            if (batchCount <= 0) {
                batchCount = DEFAULT_BATCH_COUNT_1000;
            }

            if (logger.isDebugEnabled()) {
                logger.debug("处理的数据的总数 {}， 每个批次处理的数据量 {}", size, batchCount);
            }

            //分批次处理
            for (int i = 0, batch = (int) Math.ceil(size * 1.0 / batchCount); i < batch; i++) {
                fromIndex = i * batchCount;
                toIndex = (i + 1) * batchCount;
                if (toIndex > size) {
                    toIndex = size;
                }

                if (logger.isDebugEnabled()) {
                    logger.debug("批次 {}, 下标从 {}， 下标至 {}", i, fromIndex, toIndex);
                }

                //处理数据
                if (null == result) {
                    //直接获取批次的结果
                    result = callBatchAction(i, batchDataList.subList(fromIndex, toIndex), actionFunction, biActionFunction);
                } else {
                    //对每次处理的结果进行处理
                    result = resultFunction.apply(result,
                            callBatchAction(i, batchDataList.subList(fromIndex, toIndex), actionFunction, biActionFunction));
                }
            }
        }

        if (logger.isDebugEnabled()) {
            end = LocalDateTime.now();
            logger.debug("批次处理完成... 结束时间 {}, 耗时 {}秒", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS")),
                    Duration.between(start, end).toMillis() / 1000.0);
        }

        //返回结果
        return result;
    }

    /**
     * 调用批次处理方法
     *
     * @param batchIndex       批次下标
     * @param subList          子list
     * @param actionFunction   处理逻辑对象
     * @param biActionFunction 带下标的处理逻辑对象
     * @param <T>              处理对象泛型
     * @param <R>              返回结果泛型
     * @return 处理结果
     */
    private static <T, R> R callBatchAction(Integer batchIndex, List<T> subList, Function<List<T>, R> actionFunction, BiFunction<Integer, List<T>, R> biActionFunction) {
        if (actionFunction != null) {
            //调用没有批次号的方法
            return actionFunction.apply(subList);
        } else if (biActionFunction != null) {
            //调用有批次号的方法
            return biActionFunction.apply(batchIndex, subList);
        } else {
            //参数异常
            throw new IllegalArgumentException("参数异常");
        }
    }
}
