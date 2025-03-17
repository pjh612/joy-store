package com.joy.joycommon.api.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDto<T> {
    private List<T> content;
    private Pageable page;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pageable {
        private int size;
        private int number;
        private int totalOfElements;
        private int totalPages;
    }
}
