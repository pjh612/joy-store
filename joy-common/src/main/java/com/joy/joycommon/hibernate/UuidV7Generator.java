package com.joy.joycommon.hibernate;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

@Slf4j
public class UuidV7Generator implements BeforeExecutionGenerator {
    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }

    @Override
    public boolean allowAssignedIdentifiers() {
        return true;
    }


    @Override
    public Object generate(SharedSessionContractImplementor session, Object entity, Object currentValue, EventType eventType) {
        Object entityIdValue = getEntityIdValue(entity);
        log.info("entityIdValue:{}", entityIdValue);

        if (entityIdValue == null) {
            return Generators.timeBasedEpochGenerator().generate().toString();
        } else {
            return entityIdValue;
        }
    }

    private Object getEntityIdValue(Object entity) {
        try {
            for (var field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return field.get(entity);
                }
            }
        } catch (IllegalAccessException e) {
            log.error("Failed to access ID field in entity: {}", entity.getClass().getName(), e);
        }
        return null;
    }
}
