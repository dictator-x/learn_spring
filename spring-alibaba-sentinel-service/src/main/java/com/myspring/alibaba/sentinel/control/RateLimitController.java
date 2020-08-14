package com.myspring.alibaba.sentinel.control;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.myspring.alibaba.sentinel.handler.CustomerBlockHandler;
import com.myspring.cloud.commons.domain.CommonResult;
import com.myspring.cloud.commons.domain.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "flow test ok", new Payment(2020L, "serial001"));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName()+"\t server can not use");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "url limit ok", new Payment(2020L, "serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(
            value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2"
    )
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "custom define", new Payment(2020L, "serial003"));
    }
}

