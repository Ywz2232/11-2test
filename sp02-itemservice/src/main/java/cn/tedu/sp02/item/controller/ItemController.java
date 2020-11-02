package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Value("${server.port}")
    private int port;

    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
        log.info("server.port="+port+",获取订单的商品列表, orderId="+orderId);
        List<Item> items = itemService.getItems(orderId);
        return JsonResult.ok().msg("port="+port).data(items);
    }

    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        itemService.decreaseNumber(items);
        return JsonResult.ok().msg("减少商品库存成功");
    }

}
