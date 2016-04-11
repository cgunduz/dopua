package com.cemgunduz.dopua.domain.scum.controller;

import com.cemgunduz.dopua.domain.scum.controller.input.DopuaCreationInput;
import com.cemgunduz.dopua.domain.scum.model.ScumApi;
import com.cemgunduz.dopua.session.ScumSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.logging.Logger;

/**
 * Created by cgunduz on 3/23/2016.
 */

@Controller
public class ScumRequestController {

    private static final Logger logger = Logger.getLogger("Homepage");

    @Autowired
    ScumApi scumApi;

    @Autowired
    ScumSession scumSession;

    @RequestMapping(value = "/point", method = RequestMethod.POST)
    public RedirectView awardPoint(RedirectAttributes redirectAttributes, @ModelAttribute DopuaCreationInput input)
    {
        input.setAwarderId(scumSession.getUserId());
        scumApi.giveDopua(input);

        return new RedirectView("/");
    }
}
