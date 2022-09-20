package com.lenis0012.bukkit.loginsecurity.database;

public class AsyncResult<T> {

    private boolean success;
    private T result;
    private Exception error;

    public AsyncResult(boolean success, T result, Exception error) {
        this.success = success;
        this.result = result;
        this.error = error;
    }
    
    public boolean success() {
        return success;
    }
    
    public T result() {
        return result;
    }
    
    public Exception error() {
        return error;
    }

}
