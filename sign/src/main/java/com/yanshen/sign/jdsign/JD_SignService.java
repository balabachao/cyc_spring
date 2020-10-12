package com.yanshen.sign.jdsign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class JD_SignService {

   // @Scheduled(cron = "0 00 08 * * ?")
    private void JindDong_Sign(){
        String titlte= "京东自动签到";
        try {
            Process p = null;
            String line = null;
            BufferedReader stdout = null;
            String command = "node D:\\签到\\JD_DailyBonus.js";
            System.out.println("开始签到...");
            p = Runtime.getRuntime().exec(command);
            stdout = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                // System.out.println(line);
                if (line.contains("【账号总计】")){
                    dopush(titlte,line+"?sound=newsflash");
                    System.out.println(line);
                }
                if (line.contains("脚本终止, 未获取Cookie")){
                    dopush(titlte,line+"?sound=newsflash");
                    break;
                }
            }
            stdout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Tieba Auto Sign
     * @param
     */
    @Scheduled(cron = "0 55 07 * * ?")
    public void tiebasign(){
        try {
            String title= "贴吧自动签到";
            RestTemplate template = new RestTemplate();
            String tieba = "http://c.tieba.baidu.com/c/c/forum/msign";
            long  timeNew =  System.currentTimeMillis();
            String text ="m_logid=142137758&m_cost=412.093997&subapp_type=tieba&m_size_d=282&_timestamp="+timeNew+"&brand=iPhone&_os_version=13.5&shoubai_cuid=CB294A95E516248FF386B91A6858DD4C4529B4241OMIDOADMMC&stoken=&_client_version=11.0.0&BDUSS=lhnVHAyWllaUEphMkhKYlZEcHRCdm1vNXo1cG1NdzJhZVNRZTh-WE5oV0I1S2RmSVFBQUFBJCQAAAAAAAAAAAEAAACwTTsiv-Bf0NDJrgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIFXgF-BV4BfU&_phone_newimei=E332CAE6913EBA7DFF435CBCFF9630FF&tbs=017e4c2f258868711602246529&forum_ids=1057377%2C34585%2C342572%2C1089593%2C1354647%2C1053887%2C8902%2C14114%2C609353%2C123509%2C427623%2C20972%2C1603607%2C358325%2C6695709%2C1525417%2C414003%2C1364185%2C153%2C59099%2C23504%2C15752%2C493313%2C3941766%2C16151227%2C722533%2C115634%2C15301%2C4644302%2C249887%2C16650833%2C2140059%2C92368%2C208889%2C9224366%2C17381091%2C24262%2C3027839%2C53242%2C970%2C815816%2C2602287%2C1554542%2C305839%2C310627%2C14%2C17297608%2C3069438%2C1546493%2C61215&sig=3d5f64b00ab3193980851891746a6e73&net_type=1&cuid=E332CAE6913EBA7DFF435CBCFF9630FF&sign=CC4021BD187A7C11474D508B891AE4BB&_client_type=1&lego_lib_version=3.0.0&model=iPhone%20XS&from=appstore&_client_id=wappc_1592701762178_159&m_size_u=4897&brand_type=iPhone%20XS&_phone_imei=E332CAE6913EBA7DFF435CBCFF9630FF&m_api=c%2Fs%2Fnewlog&st_type=free_mem&z_id=V-9uNIQf9nHzX7Uajxo1U6pTuXIqMGmp56zzlftPd4T7L7CGwPeMzJIo49JTlK-mNIVCbGmtBaMDGLE1fbxkGRAm_logid=142137758&m_cost=412.093997&subapp_type=tieba&m_size_d=282&_timestamp=1602349343478&brand=iPhone&_os_version=13.5&shoubai_cuid=CB294A95E516248FF386B91A6858DD4C4529B4241OMIDOADMMC&stoken=&_client_version=11.0.0&BDUSS=lhnVHAyWllaUEphMkhKYlZEcHRCdm1vNXo1cG1NdzJhZVNRZTh-WE5oV0I1S2RmSVFBQUFBJCQAAAAAAAAAAAEAAACwTTsiv-Bf0NDJrgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIFXgF-BV4BfU&_phone_newimei=E332CAE6913EBA7DFF435CBCFF9630FF&tbs=017e4c2f258868711602246529&forum_ids=1057377%2C34585%2C342572%2C1089593%2C1354647%2C1053887%2C8902%2C14114%2C609353%2C123509%2C427623%2C20972%2C1603607%2C358325%2C6695709%2C1525417%2C414003%2C1364185%2C153%2C59099%2C23504%2C15752%2C493313%2C3941766%2C16151227%2C722533%2C115634%2C15301%2C4644302%2C249887%2C16650833%2C2140059%2C92368%2C208889%2C9224366%2C17381091%2C24262%2C3027839%2C53242%2C970%2C815816%2C2602287%2C1554542%2C305839%2C310627%2C14%2C17297608%2C3069438%2C1546493%2C61215&sig=3d5f64b00ab3193980851891746a6e73&net_type=1&cuid=E332CAE6913EBA7DFF435CBCFF9630FF&sign=CC4021BD187A7C11474D508B891AE4BB&_client_type=1&lego_lib_version=3.0.0&model=iPhone%20XS&from=appstore&_client_id=wappc_1592701762178_159&m_size_u=4897&brand_type=iPhone%20XS&_phone_imei=E332CAE6913EBA7DFF435CBCFF9630FF&m_api=c%2Fs%2Fnewlog&st_type=free_mem&z_id=V-9uNIQf9nHzX7Uajxo1U6pTuXIqMGmp56zzlftPd4T7L7CGwPeMzJIo49JTlK-mNIVCbGmtBaMDGLE1fbxkGRA";
            ResponseEntity<String> stringResponseEntity = template.postForEntity(tieba, text, String.class);
            String resutlt = stringResponseEntity.getBody();
            JSONObject jsonObject1 = JSONObject.parseObject(resutlt);
            JSONObject info =jsonObject1.getJSONObject("error");
            String messages=info.getString("usermsg");
            if ("成功"==messages){
                JSONArray jsonArray =jsonObject1.getJSONArray("info");
                String m = "一键签到了："+jsonArray.size()+"个贴吧"+"?sound=healthnotification";
                dopush(title,m);
            }else {
                dopush(title,messages+"?sound=healthnotification");
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            dopush("异常",e.getMessage());
        }
    }
    @Scheduled(cron = "0 01 00 * * ?")
    public void  WuYouXingSign(){
        String title ="无忧行签到";
        try {
            RestTemplate template = new RestTemplate();

            String wyx = "http://task.jegotrip.com.cn:8080/app/sign";
            String login ="http://task.jegotrip.com.cn:8080/app/tasks?userid=f10b1d6e8f444279b8d20d8bc01fb3f8";
            String sign = "https://app.jegotrip.com.cn/api/service/user/v1/getUserAssets?lang=zh_cn&token=2421d76ff43d41c9a3cffcd60af7ee1a";
            Map<String, String> params = new HashMap<>();
            params.put("userid","f10b1d6e8f444279b8d20d8bc01fb3f8");
            params.put("taskId","2c909969630101e7016325c827520000");
            params.put("token","2421d76ff43d41c9a3cffcd60af7ee1a");
            //账号上线
        /*ResponseEntity<String> reg = template.getForEntity(login,String.class);
        JSONObject jsonObject = JSONObject.parseObject(reg.getBody());
        System.out.println(jsonObject);*/
            //Start Sign
            ResponseEntity<String> stringResponseEntity = template.postForEntity(wyx, params,String.class);
            //Get reuslt
            ResponseEntity<String> result = template.postForEntity(sign, params,String.class);
            JSONObject jsonObject = JSONObject.parseObject(result.getBody());
            System.out.println(stringResponseEntity);
            JSONObject jsoncoin =jsonObject.getJSONObject("body");
            String coin = "签到成功-账户无忧币："+jsoncoin.get("tripCoins")+"个"+"?sound=glass";
            System.out.println(jsonObject);
            dopush(title,coin);
        } catch (RestClientException e) {
            dopush(title,e.getMessage());
        }
    }



    public void dopush(String title,String message){
        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        String push = "https://api.day.app/XGJxUAwQRa4ARoKTN7Q4aB/"+title+"/"+message;
        template.postForEntity(push, params,String.class);
    }

    public static void main(String[] args) {
        JD_SignService jd_signService =new JD_SignService();
        //jd_signService.WuYouXingSign();
        jd_signService.tiebasign();
        //jd_signService.JindDong_Sign();
    }
}
