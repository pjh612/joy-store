package com.joy.joyorder.adapters.out.alarm.cache;

import java.util.List;

public interface AlarmCacheManager<T> {
    Boolean save(String key, String id, T value);

    List<T> getFromOffset(String key, Long offset, Class<T> tClass);
}
