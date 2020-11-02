package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service",fallback = OrderFeignClientFB.class)
public interface OrderFeignClient {
    //调用获取订单
    @GetMapping("/{orderId}")
    JsonResult<Order> getOrder(@PathVariable String orderId);
    //调用添加订单
    @GetMapping("/")
    JsonResult<?> addOrder();
}
