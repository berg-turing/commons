package com.bergturing.commons.result.service;

import java.util.List;

/**
 * 用于封装一个方法的返回值
 * 将方法需要返回的结果和对结果描述的一些额外信息封装
 *
 * @param <T> 结果类型
 * @author bo.he02@hand-china.com
 * @apiNote 2018/08/06
 */
public interface MethodResult<T> {
    /**
     * 设置单个结果
     *
     * @param result 单个结果
     * @return 当前对象
     */
    MethodResult<T> setSingle(T result);

    /**
     * 设置多个结果
     *
     * @param resultList 多个结果
     * @return 当前对象
     */
    MethodResult<T> setMulti(List<T> resultList);

    /**
     * 添加结果对象
     *
     * @param result 结果对象
     * @return 当前对象
     */
    MethodResult<T> add(T result);

    /**
     * 追加结果消息
     *
     * @param message 结果消息
     * @return 当前对象
     */
    MethodResult<T> append(String message);

    /**
     * 设置处理结果标识
     *
     * @param success 是否处理成功
     * @return 当前对象
     */
    MethodResult<T> setSuccess(boolean success);

    /**
     * 设置总数
     *
     * @param total 总数
     * @return 当前对象
     */
    MethodResult<T> setTotal(int total);

    /**
     * 获取结果消息
     *
     * @return 结果消息
     */
    String getMessage();

    /**
     * 是否处理成功
     *
     * @return 是否成功的标识
     */
    boolean isSuccess();

    /**
     * 获取总数
     *
     * @return 总数
     */
    int getTotal();

    /**
     * 获取单个结果消息
     * 只用与方法返回值只有一个的情况
     *
     * @return 单个结果
     */
    T getSingleResult();

    /**
     * 获取多个返回结果
     * 只用于方法返回值有多个的情况
     *
     * @return 多个结果
     */
    List<T> getMultiResult();
}
