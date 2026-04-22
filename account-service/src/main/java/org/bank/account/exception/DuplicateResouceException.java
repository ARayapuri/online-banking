package org.bank.account.exception;

public class DuplicateResouceException extends RuntimeException{

    public DuplicateResouceException(String message){
        super(message);
    }
}
