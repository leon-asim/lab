package mk.finki.ukim.mk.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(Long id) {
        super(String.format("Event with id: %d doesn't exist", id));
    }
}
