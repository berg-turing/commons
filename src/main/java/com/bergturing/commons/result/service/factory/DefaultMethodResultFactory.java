package com.bergturing.commons.result.service.factory;

import com.bergturing.commons.result.service.MethodResult;
import com.bergturing.commons.result.service.MethodResultFactory;
import com.bergturing.commons.result.service.defaults.MethodMultiResult;
import com.bergturing.commons.result.service.defaults.MethodSingleResult;
import com.bergturing.commons.result.service.defaults.MethodVoidResult;
import org.springframework.stereotype.Component;

/**
 * 方法结果封装的工厂的默认实现
 *
 * @author bo.he02@hand-china.com
 * @apiNote 2018/08/06
 * @see MethodResult
 * @see MethodResultFactory
 */
@Component
public class DefaultMethodResultFactory implements MethodResultFactory {
    @Override
    public <T> MethodResult<T> create(MethodResultType type) {
        switch (type) {
            //单个结果值
            case SINGLE:
                return this.singleResult();
            //多个结果值
            case MULTI:
                return this.multiResult();
            //没有结果值
            case VOID:
                return this.voidResult();
            //未知类型
            default:
                throw new IllegalArgumentException("undefined type");
        }
    }

    @Override
    public <T> MethodResult<T> voidResult() {
        return new MethodVoidResult<>();
    }

    @Override
    public <T> MethodResult<T> singleResult() {
        return new MethodSingleResult<>();
    }

    @Override
    public <T> MethodResult<T> multiResult() {
        return new MethodMultiResult<>();
    }
}
