package blog.AC.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class AdminReqDto {
    Long id;
    String firstName;
    String secondName;
    int currentYear;
    String currentStudyCycle;
    String email;
    String category;
    String coverLetter;
    LocalDateTime timeStamp;
}
