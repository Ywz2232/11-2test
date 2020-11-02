package cn.tedu.sp04.order.feign;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class ItemFeignClientFB implements ItemFeignClient{
    @Override
    public JsonResult<List<Item>> getItems(String orderId) {
        if(Math.random() < 0.5){
            List<Item> items = Arrays.asList(
                    new Item(1,"缓存商品1",1),
                    new Item(2,"缓存商品2",3),
                    new Item(3,"缓存商品3",2),
                    new Item(4,"缓存商品4",5),
                    new Item(5,"缓存商品4",1));
            return JsonResult.ok().data(items);
        }
        return JsonResult.err("获取商品列表失败");
    }

    @Override
    public JsonResult<?> decreaseNumber(List<Item> items) {
        return JsonResult.err("减少订单商品数失败");
    }
}
