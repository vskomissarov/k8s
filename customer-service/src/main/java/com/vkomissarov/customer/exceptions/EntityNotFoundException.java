package com.vkomissarov.customer.exceptions;

import java.util.Collection;
import java.util.UUID;

import static java.lang.String.format;

public class EntityNotFoundException extends RuntimeException {
    private final static String ENTITY_NOT_FOUND = "Entity not found";

    public EntityNotFoundException(Class clazz, UUID id) {
        super(format(ENTITY_NOT_FOUND + " '%s' '%s'", clazz.getName(), id.toString()));
    }
    public EntityNotFoundException(Class clazz, Collection<UUID> id) {
        super(format(ENTITY_NOT_FOUND + " '%s' '%s'", clazz.getName(), id.toString()));
    }
    public EntityNotFoundException(Class clazz, String findProperty) {
        super(format(ENTITY_NOT_FOUND + " '%s' by property '%s'", clazz.getName(), findProperty));
    }

    public EntityNotFoundException(Class clazz, UUID id, long number) {
        super(format(ENTITY_NOT_FOUND + " '%s' '%s' '%s'", clazz.getName(), id.toString(), number));
    }
}
