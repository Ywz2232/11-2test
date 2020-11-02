package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "user-service",fallback = UserFeignClientFB.class)
public interface UserFeignClient {
    //调用获取用户
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);
    //调用增加积分
    @GetMapping("/{userId}/score")
    JsonResult<?> addScore(@PathVariable Integer userId,@RequestParam Integer score);
}
