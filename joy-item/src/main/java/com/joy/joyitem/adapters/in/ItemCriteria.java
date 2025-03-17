package com.joy.joyitem.adapters.in;

public record ItemCriteria(
        Integer size,
        Integer page
) {
    public Integer page() {
        return page != null ? page : 0; // 기본값 0
    }

    public Integer size() {
        return size != null ? size : Integer.MAX_VALUE; // 기본값: 모든 데이터
    }
}
