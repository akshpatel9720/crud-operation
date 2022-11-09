package com.example.taskcrudoperation.schedular;

import com.example.taskcrudoperation.model.UserEntity;
import com.example.taskcrudoperation.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class LoginSchedular {

    public static final Logger logger = LoggerFactory.getLogger(LoginSchedular.class);

    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = "*/5 * * * * ?")
    public void timeschedular() {
        try {
            logger.info("inside for loop");
            List<UserEntity> users = userRepository.getInActiveUser();
            for (UserEntity userEntity : users) {
                logger.info("inside for loop");
                Date lastDate = userEntity.getCreatedDateTime();
                Date date = getupdateDate(lastDate, 0);
                if (date.before(new Date())) {
                    logger.info("inside if condition");
                    userEntity.setVerified(true);
                    userEntity.setUpdateDateTime(new Date());
                    logger.info("before save fields");
                    userRepository.save(userEntity);
                    logger.info("after save data");
                }
            }
        } catch (Exception e) {
            logger.info("Inside Catch block");
            e.printStackTrace();
        }
    }

    private Date getupdateDate(Date lastDate, Integer hour) {
        logger.info("inside");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        logger.info("before add to calendar");
        calendar.add(Calendar.HOUR, hour);
        logger.info("after add to calendar");
        return calendar.getTime();
    }
}
