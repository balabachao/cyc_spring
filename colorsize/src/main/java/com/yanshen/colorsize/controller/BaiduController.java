package com.yanshen.colorsize.controller;
import com.alibaba.fastjson.JSONObject;
import com.yanshen.colorsize.entity.BaiduEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baidu")
public class BaiduController {
    @RequestMapping("/do")
    public String DealJPG() {

        String result = Colourize.colourize();
        BaiduEntity entity = new BaiduEntity();
        JSONObject jsonObject1 =JSONObject.parseObject(result);
        entity.setImage(result);
        String res= "data:image/jpg;base64,"+jsonObject1.getString("image");

        return res;
    }
}
