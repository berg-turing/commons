package com.bergturing.commons.result.service;


import com.bergturing.commons.result.service.defaults.MethodMultiResult;
import com.bergturing.commons.result.service.defaults.MethodSingleResult;
import com.bergturing.commons.result.service.defaults.MethodVoidResult;

/**
 * 方法结果封装的工厂接口
 *
 * @author bo.he02@hand-china.com
 * @apiNote 2018/08/06
 * @see MethodResult
 */
public interface MethodResultFactory {
    /**
     * 没有返回值的结果封装对象
     *
     * @return 没有返回值的结果封装对象
     */
    static MethodVoidResult voidResult() {
        return MethodVoidResult.of();
    }

    /**
     * 单个返回值的结果封装对象
     *
     * @param <T> 结果返回类型
     * @return 单个返回值的结果封装对象
     */
    static <T> MethodSingleResult<T> singleResult() {
        return MethodSingleResult.of();
    }

    /**
     * 多个返回值的结果封装对象
     *
     * @param <T> 结果返回类型
     * @return 多个返回值的结果封装对象
     */
    static <T> MethodMultiResult<T> multiResult() {
        return MethodMultiResult.of();
    }

    /**
     * 没有返回值的结果封装对象
     *
     * @param success 结果标识
     * @return 没有返回值的结果封装对象
     */
    static MethodVoidResult voidResult(boolean success) {
        return MethodVoidResult.of(success);
    }

    /**
     * 单个返回值的结果封装对象
     *
     * @param success 结果标识
     * @param <T>     结果返回类型
     * @return 单个返回值的结果封装对象
     */
    static <T> MethodSingleResult<T> singleResult(boolean success) {
        return MethodSingleResult.of(success);
    }

    /**
     * 多个返回值的结果封装对象
     *
     * @param success 结果标识
     * @param <T>     结果返回类型
     * @return 多个返回值的结果封装对象
     */
    static <T> MethodMultiResult<T> multiResult(boolean success) {
        return MethodMultiResult.of(success);
    }
}
