package com.project.actionexecutor.scheduler;

import com.project.actionexecutor.service.ActionExecutorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ActionExecutorScheduler {
    private final ActionExecutorService executorService;

    public ActionExecutorScheduler(ActionExecutorService executorService) {
        this.executorService = executorService;
    }

    @Scheduled(cron = "${schedule}")
    public void executeActions() {
        this.executorService.executeActionsWhichAreConfiguredForGivenDayAndTime(LocalDate.now().getDayOfWeek(), LocalTime.now());
    }
}
