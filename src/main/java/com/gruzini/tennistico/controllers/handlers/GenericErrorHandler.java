package com.gruzini.tennistico.controllers.handlers;

import com.gruzini.tennistico.messages.ErrorMessages;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenericErrorHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(final Model model) {
        model.addAttribute("message", ErrorMessages.ILLEGAL_ACTION_MESSAGE);
        return "error-page";
    }
}
