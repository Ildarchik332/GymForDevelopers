package com.dev.GymForDevelopers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    IN_REVIEW(0, "Заметка на рассмотрение"),
    ACCEPTED(1, "Заметка принята"),
    REJECTED(2, "Заметка отклоненна"),
    DELETED(3, "Заметка удалена"),
    RECOVERED(4, "Заметка востановленна");

    private final Integer code;
    private final String description;

}
