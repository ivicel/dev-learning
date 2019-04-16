package spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "404 Not Found")
public class SpittleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3779230077375676450L;
}
