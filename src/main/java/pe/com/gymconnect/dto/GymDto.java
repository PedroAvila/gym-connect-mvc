package pe.com.gymconnect.dto;

import java.util.Date;

public record GymDto(Long id, int code, String name, String address, String phone,

        Date creationDate) {

}
