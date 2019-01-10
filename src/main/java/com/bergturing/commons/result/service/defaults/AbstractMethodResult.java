package com.bergturing.commons.result.service.defaults;

import com.bergturing.commons.result.service.MethodResult;

import java.util.List;

/**
 * @param <T> 结果类型
 * @author bo.he02@hand-china.com
 * @apiNote 2018/08/06
 * @see MethodResult 接口的抽象实现，实现了内部的通用功能
 */
public abstract class AbstractMethodResult<T> implements MethodResult<T> {
    /**
     * 结果标识
     */
    private boolean success;

    /**
     * 计数
     */
    private int total;

    /**
     * 结果消息
     */
    private StringBuilder message;

    AbstractMethodResult(boolean success) {
        this.success = success;
        this.message = new StringBuilder();
    }

    @Override
    public final MethodResult<T> setSingle(T result) {
        setSingleResult(result);
        return this;
    }

    @Override
    public final MethodResult<T> setMulti(List<T> resultList) {
        setMultiResult(resultList);
        return this;
    }

    @Override
    public final MethodResult<T> add(T result) {
        addResult(result);
        return this;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public final MethodResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public final MethodResult<T> setTotal(int total) {
        this.total = total;
        return this;
    }

    @Override
    public final String getMessage() {
        return message.toString();
    }

    @Override
    public final MethodResult<T> append(String message) {
        this.message.append(message);
        return this;
    }

    /**
     * 设置单个单个结果对象
     *
     * @param result 结果对象
     */
    protected abstract void setSingleResult(T result);

    /**
     * 设置多个结果对象
     *
     * @param resultList 结果对象
     */
    protected abstract void setMultiResult(List<T> resultList);

    /**
     * 添加结果对象
     *
     * @param result 即将添加的对象
     */
    protected abstract void addResult(T result);
}
