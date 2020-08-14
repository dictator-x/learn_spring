package com.myspring.alibaba.sentinel.handler;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.myspring.cloud.commons.domain.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exceptino) {
        return new CommonResult(4444, "customer define, global-----1");
    }
    public static CommonResult handlerException2(BlockException exceptino) {
        return new CommonResult(4444, "customer define, global-----2");
    }
}
