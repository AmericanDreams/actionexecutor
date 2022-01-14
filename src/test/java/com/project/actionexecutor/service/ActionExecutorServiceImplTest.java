package com.project.actionexecutor.service;

import com.project.actionexecutor.dto.ActionDetail;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.project.actionexecutor.service.impl.ActionExecutorServiceImpl.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActionExecutorServiceImplTest {

    @Test
    public void isActionConfiguredForCurrentDayOfWeekTest() {
        ActionDetail mondayAction = new ActionDetail();
        mondayAction.setBitmask(64); // 1000000
        assertTrue(isActionConfiguredForGivenDayOfWeek(mondayAction, DayOfWeek.MONDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAction, DayOfWeek.TUESDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAction, DayOfWeek.WEDNESDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAction, DayOfWeek.THURSDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAction, DayOfWeek.FRIDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAction, DayOfWeek.SATURDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAction, DayOfWeek.SUNDAY));

        ActionDetail tuesdayAction = new ActionDetail();
        tuesdayAction.setBitmask(32); // 0100000
        assertFalse(isActionConfiguredForGivenDayOfWeek(tuesdayAction, DayOfWeek.MONDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(tuesdayAction, DayOfWeek.TUESDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(tuesdayAction, DayOfWeek.WEDNESDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(tuesdayAction, DayOfWeek.THURSDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(tuesdayAction, DayOfWeek.FRIDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(tuesdayAction, DayOfWeek.SATURDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(tuesdayAction, DayOfWeek.SUNDAY));

        ActionDetail everyDayAction = new ActionDetail();
        everyDayAction.setBitmask(127); // 1111111
        assertTrue(isActionConfiguredForGivenDayOfWeek(everyDayAction, DayOfWeek.MONDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(everyDayAction, DayOfWeek.TUESDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(everyDayAction, DayOfWeek.WEDNESDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(everyDayAction, DayOfWeek.THURSDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(everyDayAction, DayOfWeek.FRIDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(everyDayAction, DayOfWeek.SATURDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(everyDayAction, DayOfWeek.SUNDAY));

        ActionDetail mondayAndSaturdayAction = new ActionDetail();
        mondayAndSaturdayAction.setBitmask(66); // 1000010
        assertTrue(isActionConfiguredForGivenDayOfWeek(mondayAndSaturdayAction, DayOfWeek.MONDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAndSaturdayAction, DayOfWeek.TUESDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAndSaturdayAction, DayOfWeek.WEDNESDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAndSaturdayAction, DayOfWeek.THURSDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAndSaturdayAction, DayOfWeek.FRIDAY));
        assertTrue(isActionConfiguredForGivenDayOfWeek(mondayAndSaturdayAction, DayOfWeek.SATURDAY));
        assertFalse(isActionConfiguredForGivenDayOfWeek(mondayAndSaturdayAction, DayOfWeek.SUNDAY));
    }

    @Test
    public void isActionConfiguredForGivenTimeOfDayTest() {
        ActionDetail action1 = new ActionDetail();
        action1.setTime(LocalTime.parse("10:10"));
        assertTrue(isActionConfiguredForGivenTimeOfDay(action1, LocalTime.of(10, 10)));
        assertFalse(isActionConfiguredForGivenTimeOfDay(action1, LocalTime.of(10, 20)));

        ActionDetail action2 = new ActionDetail();
        action2.setTime(LocalTime.parse("00:00"));
        assertTrue(isActionConfiguredForGivenTimeOfDay(action2, LocalTime.of(0, 0)));
        assertFalse(isActionConfiguredForGivenTimeOfDay(action2, LocalTime.of(0, 1)));

        ActionDetail action3 = new ActionDetail();
        action3.setTime(LocalTime.parse("20:22"));
        assertTrue(isActionConfiguredForGivenTimeOfDay(action3, LocalTime.of(20, 22)));
        assertFalse(isActionConfiguredForGivenTimeOfDay(action3, LocalTime.of(5, 1)));
    }

    @Test
    public void isActionConfiguredForNowTest() {
        ActionDetail actionDetail = new ActionDetail();
        actionDetail.setTime(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))));
        actionDetail.setBitmask(127);
        assertTrue(isActionConfiguredForGivenDayAndTime(actionDetail, LocalDate.now().getDayOfWeek(), LocalTime.now()));
    }
}
