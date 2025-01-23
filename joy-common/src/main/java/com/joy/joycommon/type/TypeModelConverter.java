package com.joy.joycommon.type;

import org.springframework.core.convert.converter.Converter;

public class TypeModelConverter implements Converter<TypeModel, String> {
    @Override
    public String convert(TypeModel source) {
        return source.getDescription();
    }
}
