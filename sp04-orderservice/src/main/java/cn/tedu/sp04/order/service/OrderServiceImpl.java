package cn.tedu.sp04.order.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.sp04.order.feign.ItemFeignClient;
import cn.tedu.sp04.order.feign.UserFeignClient;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemFeignClient itemFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Order getOrder(String orderId) {
        JsonResult<List<Item>> items = itemFeignClient.getItems(orderId);
        JsonResult<User> user = userFeignClient.getUser(8);
        //TODO: 远程调用商品服务,获取商品
        //TODO: 远程调用用户.获取用户数据

        Order order = new Order();
        order.setId(orderId);
        order.setItems(items.getData());
        order.setUser(user.getData());

        return order;
    }

    @Override
    public void addOrder(Order order) {
        itemFeignClient.decreaseNumber(order.getItems());
        userFeignClient.addScore(order.getUser().getId(), 100);
        log.info("保存订单"+order);
    }
}
