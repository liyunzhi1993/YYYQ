package yyyq.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActionResultModel<T> implements Serializable{
    public T data;
    private Boolean hasError=false ;
    private String errorMessage;

    public ActionResultModel(Boolean hasError, String errorMessage, T data){
        this.data = data;
        this.errorMessage = errorMessage;
        this.hasError =hasError;
    }
    private ActionResultModel(Boolean hasError, T data){
        this.data = data;
        this.hasError =hasError;
    }

    private ActionResultModel(Boolean hasError, String errorMessage){
        this.errorMessage = errorMessage;
        this.hasError =hasError;
    }

    public ActionResultModel<T> setActionResult(T data) {
        this.data = data;
        return this;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public ActionResultModel<T> setHasError(Boolean hasError) {
        this.hasError = hasError;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ActionResultModel<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * 设置hasError=true
     * @param errorMessage
     * @return
     */
    public ActionResultModel<T> setHasErrorMessage(String errorMessage) {
        this.hasError=true;
        this.errorMessage = errorMessage;
        return this;
    }

    public ActionResultModel() {
    }
}
