package com.bergturing.commons.result.service.defaults;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 单个返回值的类型
 *
 * @param <T> 返回值的类型
 * @author bo.he02@hand-china.com
 * @apiNote 2018/08/06
 * @see AbstractMethodResult
 */
public class MethodSingleResult<T> extends AbstractMethodResult<T> {
    /**
     * 结果值
     */
    private T result;

    /**
     * 构造方法
     *
     * @param success 处理结果标识
     */
    private MethodSingleResult(boolean success) {
        super(success);
    }

    /**
     * 无参工厂方法
     *
     * @param <T> 结果类型的泛型
     * @return 单个返回值类型对象
     */
    public static <T> MethodSingleResult<T> of() {
        return MethodSingleResult.of(true);
    }

    /**
     * 有一个成功标识的工厂方法
     *
     * @param success 成功标识
     * @param <T>     结果类型的泛型
     * @return 单个返回值类型对象
     */
    public static <T> MethodSingleResult<T> of(boolean success) {
        return new MethodSingleResult<>(success);
    }

    @Override
    public T getSingleResult() {

        return this.result;
    }

    @Override
    public List<T> getMultiResult() {

        return Collections.<T>singletonList(this.result);
    }

    @Override
    protected void setSingleResult(T result) {

        this.result = result;
    }

    @Override
    protected void setMultiResult(List<T> resultList) {

        if (CollectionUtils.isNotEmpty(resultList) && resultList.size() == 1) {
            this.result = resultList.get(0);
        } else {
            throw new RuntimeException("can't add multi result");
        }
    }

    @Override
    protected void addResult(T result) {

        this.result = result;
    }
}
