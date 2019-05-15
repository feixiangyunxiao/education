package com.bootdo;

import com.bootdo.welcome.domain.MycarouselDO;
import com.bootdo.welcome.service.MycarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduClass {

    @Autowired
    private MycarouselService mycarouselService;

    public void getMyCarousel() {
        List<MycarouselDO> list = mycarouselService.list(null);
        for (MycarouselDO mycarouselDO : list) {
            System.out.println(mycarouselDO);
        }
    }
}
