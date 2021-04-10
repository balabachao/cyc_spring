package com.yanshen.jdbean;

import com.yanshen.jdbean.schedule.JDScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
@SpringBootTest(classes = JdBeanApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PushTest {
    @Resource
    JDScheduler jdScheduler;
    @Test
    public void doit(){
        jdScheduler.getbeanData();
    }
}
