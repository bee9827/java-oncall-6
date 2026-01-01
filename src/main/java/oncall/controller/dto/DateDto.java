package oncall.controller.dto;

import oncall.model.DayOfWeek;

public record DateDto(
        int month,
        DayOfWeek dayOfWeek
) {
}
