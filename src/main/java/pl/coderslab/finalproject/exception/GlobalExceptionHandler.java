package pl.coderslab.finalproject.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public ModelAndView handleDuplicateUserException(DuplicateUserException ex, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error_message", ex.getMessage());
        modelAndView.setViewName("initial_registration"); // Default to user creation view
        return modelAndView;
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView handleDuplicateEmailException(DuplicateEmailException ex, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error_message", ex.getMessage());
        modelAndView.setViewName("initial_registration");
        return modelAndView;
    }

    @ExceptionHandler(DuplicatePlaceNameException.class)
    public ModelAndView handleDuplicatePlaceName(DuplicatePlaceNameException ex, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error_message", ex.getMessage());
        modelAndView.setViewName("initial_add_place");
        return modelAndView;
    }



}
