package pl.coderslab.finalproject.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public ModelAndView handleUserAlreadyExistsException(DuplicateUserException ex) {
        ModelAndView modelAndView = new ModelAndView("initial_registration"); // Change to your registration view name
        modelAndView.addObject("error_message", ex.getMessage());
        return modelAndView;
    }

    // Handle other exceptions similarly
}
