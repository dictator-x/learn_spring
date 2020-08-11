package com.myspring.cloud.demo1.order.lb;

import org.springframework.cloud.client.ServiceInstance;
import java.util.List;

public interface ILB {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
