package pe.com.gymconnect.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private HttpStatus status;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public BusinessException(String format, HttpStatus status, Object... args) {
        super(String.format(format, args));
        this.message = String.format(format, args);
        this.status = status;
    }

    // public BusinessException(String format, Object... args) {
    // super(String.format(format, args));
    // this.message = String.format(format, args);
    // Object dato = Arrays.stream(args).findFirst().orElse(null);
    // if (dato != null) {
    // this.status = (HttpStatus) dato;
    // }
    // }

}
