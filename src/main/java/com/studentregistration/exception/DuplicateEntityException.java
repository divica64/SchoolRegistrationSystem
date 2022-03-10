package com.studentregistration.exception;


public class DuplicateEntityException extends EntityException {
    public DuplicateEntityException(Class clazz, String... searchParamsMap) {
        super(clazz, " was already found for parameters ", searchParamsMap);
    }
}
