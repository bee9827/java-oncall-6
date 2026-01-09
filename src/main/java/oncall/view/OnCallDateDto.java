package oncall.view;


import oncall.model.DayOfWeek;

public record OnCallDateDto(
        int month,
        DayOfWeek dayOfWeek
) {
}
