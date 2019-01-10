package com.bergturing.commons.result.service.defaults;

import java.util.List;

/**
 * 没有值的结果类型
 *
 * @author bo.he02@hand-china.com
 * @apiNote 2018/09/29
 */
public class MethodVoidResult<T> extends AbstractMethodResult<T> {

    public MethodVoidResult() {
        this(true);
    }

    public MethodVoidResult(boolean flag) {
        this(flag, "");
    }

    public MethodVoidResult(boolean flag, String message) {
        super(flag, new StringBuilder(message));
    }

    @Override
    protected void setSingleResult(T result) {
        throw new RuntimeException("can't use");
    }

    @Override
    protected void setMultiResult(List<T> resultList) {
        throw new RuntimeException("can't use");
    }

    @Override
    protected void addResult(T result) {
        throw new RuntimeException("can't use");
    }

    @Override
    public T getSingleResult() {
        throw new RuntimeException("can't use");
    }

    @Override
    public List<T> getMultiResult() {
        throw new RuntimeException("can't use");
    }
}
