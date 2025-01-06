package blog.AC.domain.mappers;
import blog.AC.domain.dto.SignUpDto;
import org.springframework.context.annotation.Bean;


public interface Mapper<A ,B> {
    B mapTo(A a);
    A mapFrom(B b);

}
