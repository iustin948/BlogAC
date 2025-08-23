package blog.AC.controllers;

import blog.AC.domain.dto.AdminReqDto;
import blog.AC.services.AdminReqService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apply")
@AllArgsConstructor
public class ApplyController {

    private final AdminReqService adminReqService;

    @PostMapping
    public ResponseEntity<Void> apply(@RequestBody AdminReqDto adminReqDto) {
        adminReqService.save(adminReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
