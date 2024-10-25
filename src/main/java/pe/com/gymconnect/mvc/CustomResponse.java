package pe.com.gymconnect.mvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse<T> {

    private HttpStatus status;
    private T data;
    private List<String> errors;

    public CustomResponse(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public CustomResponse(HttpStatus status, String mensaje) {
        this.status = status;
        this.errors = Arrays.stream(mensaje.split("\r\n"))
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    public int getStatusCode() {
        return status.value();
    }

    // public void setStatus(int status) {
    // this.status = status;
    // }

    // public T getData() {
    // return data;
    // }

    // public void setData(T data) {
    // this.data = data;
    // }

    // public List<String> getErrors() {
    // return errors;
    // }

    // public void setErrors(List<String> errors) {
    // this.errors = errors;
    // }

}
