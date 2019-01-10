package com.bergturing.commons.result.service.defaults;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 多个返回值的类型
 *
 * @param <T> 返回值的类型
 * @author bo.he02@hand-china.com
 * @apiNote 2018/08/06
 * @see AbstractMethodResult
 */
public class MethodMultiResult<T> extends AbstractMethodResult<T> {
    /**
     * 多个返回值的结果
     */
    private List<T> result;

    /**
     * 构造方法
     *
     * @param success 处理结果标识
     */
    private MethodMultiResult(boolean success) {
        super(success);
    }

    /**
     * 无参工厂方法
     *
     * @param <T> 结果类型的泛型
     * @return 多个返回值类型对象
     */
    public static <T> MethodMultiResult<T> of() {
        return MethodMultiResult.of(true);
    }

    /**
     * 有一个成功标识的工厂方法
     *
     * @param success 成功标识
     * @param <T>     结果类型的泛型
     * @return 多个返回值类型对象
     */
    public static <T> MethodMultiResult<T> of(boolean success) {
        return new MethodMultiResult<>(success);
    }

    @Override
    protected void setSingleResult(T result) {
        this.result = Collections.singletonList(result);
    }

    @Override
    protected void setMultiResult(List<T> resultList) {
        this.result = new ArrayList<>(resultList.size());
        this.result.addAll(resultList);
    }

    @Override
    protected void addResult(T result) {
        if (null == this.result) {
            this.result = new ArrayList<T>();
        }
        this.result.add(result);
    }

    @Override
    public T getSingleResult() {
        if (CollectionUtils.isNotEmpty(this.result) && this.result.size() == 1) {
            return this.result.get(0);
        }
        throw new RuntimeException("no result or has multi result");
    }

    @Override
    public List<T> getMultiResult() {
        return this.result;
    }
}
