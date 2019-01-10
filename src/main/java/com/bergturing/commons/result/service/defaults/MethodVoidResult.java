package com.bergturing.commons.result.service.defaults;

import java.util.List;

/**
 * 没有值的结果类型
 *
 * @author bo.he02@hand-china.com
 * @apiNote 2018/09/29
 */
public class MethodVoidResult extends AbstractMethodResult<Void> {

    /**
     * 构造方法
     *
     * @param success 处理结果标识
     */
    private MethodVoidResult(boolean success) {
        super(success);
    }

    /**
     * 无参工厂方法
     *
     * @return 单个返回值类型对象
     */
    public static MethodVoidResult of() {
        return MethodVoidResult.of(true);
    }

    /**
     * 有一个成功标识的工厂方法
     *
     * @param success 成功标识
     * @return 单个返回值类型对象
     */
    public static MethodVoidResult of(boolean success) {
        return new MethodVoidResult(success);
    }

    @Override
    protected void setSingleResult(Void result) {
        throw new RuntimeException("can't use");
    }

    @Override
    protected void setMultiResult(List<Void> resultList) {
        throw new RuntimeException("can't use");
    }

    @Override
    protected void addResult(Void result) {
        throw new RuntimeException("can't use");
    }

    @Override
    public Void getSingleResult() {
        throw new RuntimeException("can't use");
    }

    @Override
    public List<Void> getMultiResult() {
        throw new RuntimeException("can't use");
    }
}