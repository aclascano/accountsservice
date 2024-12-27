package com.aldev.accountservice.exception;

public class EntityDuplicateException extends AppBaseException {

    public EntityDuplicateException(String entity, String identifier) {
        super(entity + " con identificacion:" + identifier + "ya existe.");
    }
}
