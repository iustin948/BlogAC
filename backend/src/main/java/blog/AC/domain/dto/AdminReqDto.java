package blog.AC.domain.dto;

import lombok.Data;

@Data
public class AdminReqDto {
    private String firstName;
    private String secondName;
    private int currentYear;
    private String currentStudyCycle;
    private String Email;
    private String category;
    private String coverLetter;

}
