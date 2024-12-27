package com.aldev.accountservice.exception;

public class EntityNotFoundException extends AppBaseException {

    public EntityNotFoundException(String entity, String id) {
        super(entity + " con el ID: " + id + " no se ha encontrado");
    }
}
