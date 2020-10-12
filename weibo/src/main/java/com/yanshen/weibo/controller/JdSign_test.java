package com.yanshen.weibo.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class JdSign_test {
    public static void main(String[] args) throws InterruptedException {
       String cookie ="shshshfpa=3c43804f-95d6-f5df-6cbb-9b24827a6274-1591240350; pinId=kBJyyloOLo54t4Y06rqR_Q; shshshfpb=lYSQa%20aT63Opi%20wKWiE%2FhDA%3D%3D; whwswswws=; TrackID=1N6smcSmLZDWg7fFhJ7ElhOwHijNn8rS-7IbBUDeRksex-30rElk0xOILCZzYFahE4NRX4EIRH7Pqkiu_zUtLb-T8iT50XB6NNoD-zLI-5Ws; unpl=V2_ZzNtbRAHEBAiDkJRex8LA2IHRltKUEIUIg0TV3tMDAZhBRNdclRCFnQUR1xnGVoUZwUZXkZcQRZFCEJkexhdBGIDFFlDX3MlfQAoVDYZMgYJA18QD2dAFUUIR2R7HVQFZAYTX0dfQBB1CEVXchxaBGcCFm1yV0UlRVMTOn8RVVIzCxVaSwATRXY4R2R6KV01LG0TEEJTSxV2DUdWfhFfAGcDEV5LUkUUdQlCZHopXw%3d%3d; shshshfp=72f1ed1d25599b36a3178e4e35b70f25; 3AB9D23F7A4B3C9B=SX46KPEJGWRAD3D37KTZYRQ6RIHTYIHHBGEKNZLGGZHCCSLVKYY2MGGCOAQL3C74NSLBKU57RXW6HCAYQKXA2IXMTY; jcap_dvzw_fp=013a8b763d8f4d0adec32eeef54c660f$874760709309; __jda=122270672.15912403492411228470105.1591240349.1601350538.1602220657.28; __jdv=122270672|direct|-|none|-|1602220657439; __jdc=122270672; ipLoc-djd=15-1213-1214-0; areaId=15; shshshsID=05768e576d04137ebc996f634325d8b2_2_1602232032280; TrackerID=gjuPwiVHGW1YxnvCaSo-dGY3MfX6eSlIADPQlPQKxpAhnWTAecS-IfVf4vanMWQSkRqGuLlo8lA11Ty51-GwE9I3rSZ32vwPj1ztcK3ltQoyhcJXECstH-u4-QGnaozg; pt_key=AAJfgB7wADDBbnSD9kXtaC80z794TeyplbPjC4ueMb8r4IWKGc95gPiZXLvnwC2P422e07oTnaU; pt_pin=%E5%BD%A6%E8%B6%85100; pt_token=n3ow20vy; pwdt_id=%E5%BD%A6%E8%B6%85100; mobilev=html5";
        JdSign_test jdSign_test = new JdSign_test();
        //jdSign_test.dosign(cookie);
/*        for (int i = 0; i <100 ; i++) {
            jdSign_test.testpush(i);
            Thread.sleep(500);
        }*/
        jdSign_test.sign();
    }

    private HttpHeaders getHeader(String cookie) {
        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //headers.put("cookie",cookie);
        return headers;
    }

    public  static List<String> dealCookie(String cookie){
       String a = cookie.replace(" ","");
        String[] strings = a.split(";");
        List<String> list = Arrays.asList(strings);
        List<String> cookies = new ArrayList<>();
        cookies.addAll(list);
        System.out.println(list);
        return cookies;
    }


    public void sign(){
        RestTemplate template = new RestTemplate();
        String url = "https://wq.jd.com/user/info/QueryJDUserInfo?sceneval=2";
        try {
            Process p = null;
            String line = null;
            BufferedReader stdout = null;
            String command = "node C:\\Users\\cuiyanchao\\Desktop\\签到\\JD_DailyBonus.js";
            p = Runtime.getRuntime().exec(command);
            stdout = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
            }
            stdout.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dosign(String cookies){
        RestTemplate template = new RestTemplate();
        String push = "https://api.day.app/XGJxUAwQRa4ARoKTN7Q4aB/签到成功获得京豆100个";
        String url = "https://wq.jd.com/user/info/QueryJDUserInfo?sceneval=2";
        HttpHeaders headers = new HttpHeaders();
        //headers.put("cookie",dealCookie(cookies));
       headers.put(HttpHeaders.COOKIE,dealCookie(cookies));
        headers.add("Referer","https://wqs.jd.com/my/jingdou/my.shtml?sceneval=2");
        Map<String, String> params = new HashMap<>();
        template.postForEntity(push, params,String.class);
        ResponseEntity<String> response = template.postForEntity(url, params,String.class);
        String result = response.getBody();
        JSONObject ipinfo = JSONObject.parseObject(result);
        System.out.println(ipinfo);


    }
    public void testpush(int i){
        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        String push = "https://api.day.app/XGJxUAwQRa4ARoKTN7Q4aB/签到成功获得京豆"+i+"个";
        template.postForEntity(push, params,String.class);
    }
}
