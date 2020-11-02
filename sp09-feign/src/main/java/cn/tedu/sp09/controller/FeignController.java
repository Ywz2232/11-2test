package cn.tedu.sp09.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp09.feign.ItemFeignClient;
import cn.tedu.sp09.feign.OrderFeignClient;
import cn.tedu.sp09.feign.UserFeignClient;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FeignController {
    //注入声明式客户端接口对象
    @Autowired
    private ItemFeignClient itemFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private OrderFeignClient orderFeignClient;

    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId){
        return itemFeignClient.getItems(orderId);
    }

    @PostMapping("item-service/decreaseNumber")
    public  JsonResult<?> decreaseNumber(@RequestBody List<Item> items){
        return itemFeignClient.decreaseNumber(items);
    }

    @GetMapping("/user-service/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId){
        return userFeignClient.getUser(userId);
    }

    @GetMapping("/user-service/{userId}/score")
    public JsonResult<?> addScore(@PathVariable Integer userId,@RequestParam Integer score){
        return userFeignClient.addScore(userId, score);
    }

    @GetMapping("/order-service/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        return orderFeignClient.getOrder(orderId);
    }

    @GetMapping("/order-service")
    public JsonResult<?> addOrder(){
        return orderFeignClient.addOrder();
    }
}
