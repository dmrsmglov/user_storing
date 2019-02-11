package ru.damir.server.user_storing.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Pagination {
    @NotNull
    private int limit;
    @NotNull
    private int offset;
}
