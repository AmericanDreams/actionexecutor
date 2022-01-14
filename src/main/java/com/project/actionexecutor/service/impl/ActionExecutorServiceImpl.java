package com.project.actionexecutor.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.project.actionexecutor.dto.ActionDetail;
import com.project.actionexecutor.service.ActionExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionExecutorServiceImpl implements ActionExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionExecutorServiceImpl.class);

    @Value("classpath:actions.csv")
    private Resource resource;

    public static boolean isActionConfiguredForGivenDayAndTime(ActionDetail actionDetail, DayOfWeek day, LocalTime time) {
        return isActionConfiguredForGivenDayOfWeek(actionDetail, day)
                && isActionConfiguredForGivenTimeOfDay(actionDetail, time);
    }

    public static boolean isActionConfiguredForGivenDayOfWeek(ActionDetail actionDetail, DayOfWeek day) {
        return actionDetail.getConfiguredDays().contains(day);
    }

    public static boolean isActionConfiguredForGivenTimeOfDay(ActionDetail actionDetail, LocalTime time) {
        return actionDetail.getTime().compareTo(LocalTime.of(time.getHour(), time.getMinute())) == 0;
    }

    @Override
    public void executeActionsWhichAreConfiguredForGivenDayAndTime(DayOfWeek day, LocalTime time) {
        LOGGER.info("action execution started...");

        this.getActionsWhichAreConfiguredForGivenDayAndTime(day, time).forEach(a -> {
            // Execute action
            LOGGER.info("ACTION IS EXECUTED: {}", a);
        });

        LOGGER.info("action execution done");
    }

    private List<ActionDetail> getActionsWhichAreConfiguredForGivenDayAndTime(DayOfWeek day, LocalTime time) {
        List<ActionDetail> actionDetailList = new ArrayList<>();
        try {
            return actionDetailList = new CsvToBeanBuilder<ActionDetail>(new FileReader(resource.getFile()))
                    .withType(ActionDetail.class)
                    .withSkipLines(1)
                    .build()
                    .parse()
                    .stream()
                    .filter(a -> isActionConfiguredForGivenDayAndTime(a, day, time))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Error occurred when parsing file, {}", e.getMessage());
            return actionDetailList;
        }
    }
}
