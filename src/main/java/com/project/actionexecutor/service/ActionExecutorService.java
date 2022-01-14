package com.project.actionexecutor.service;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface ActionExecutorService {
    void executeActionsWhichAreConfiguredForGivenDayAndTime(DayOfWeek day, LocalTime time);
}
