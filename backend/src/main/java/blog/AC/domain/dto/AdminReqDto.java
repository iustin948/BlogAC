package blog.AC.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminReqDto {
    private String firstName;
    private String secondName;
    private int currentYear;
    private String currentStudyCycle;
    private String email;
    private String category;
    private String coverLetter;
    private LocalDateTime timeStamp;
}
