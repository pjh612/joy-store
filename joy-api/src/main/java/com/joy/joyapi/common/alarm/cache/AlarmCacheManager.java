package com.joy.joyapi.common.alarm.cache;

import java.util.List;

public interface AlarmCacheManager<T> {
    Boolean save(String key, String id, T value);

    List<T> getFromOffset(String key, Long offset, Class<T> tClass);
}
