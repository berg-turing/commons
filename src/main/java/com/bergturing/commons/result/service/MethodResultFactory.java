package com.bergturing.commons.result.service;


import com.bergturing.commons.result.service.MethodResult;
import com.bergturing.commons.result.service.factory.MethodResultType;

/**
 * 方法结果封装的工厂接口
 *
 * @author bo.he02@hand-china.com
 * @apiNote 2018/08/06
 * @see MethodResult
 */
public interface MethodResultFactory {
    /**
     * 创建结果封装对象
     *
     * @param type 方法结果的类型
     * @param <T>  方法结果类型
     * @return 创建的方法结果对象
     */
    <T> MethodResult<T> create(MethodResultType type);

    /**
     * 没有返回值的结果封装对象
     *
     * @return 没有返回值的结果封装对象
     */
    <T> MethodResult<T> voidResult();

    /**
     * 单个返回值的结果封装对象
     *
     * @param <T> 结果返回类型
     * @return 单个返回值的结果封装对象
     */
    <T> MethodResult<T> singleResult();

    /**
     * 多个返回值的结果封装对象
     *
     * @param <T> 结果返回类型
     * @return 多个返回值的结果封装对象
     */
    <T> MethodResult<T> multiResult();
}
