package com.cemgunduz.dopua;

import com.cemgunduz.dopua.domain.history.model.HistoryApi;
import com.cemgunduz.dopua.domain.history.persistence.HistoryDto;
import com.cemgunduz.dopua.domain.scum.model.Scum;
import com.cemgunduz.dopua.domain.scum.model.ScumApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by cgunduz on 3/23/2016.
 */

@Controller
public class HomepageController {

    private static final Logger logger = Logger.getLogger("Homepage");

    @Autowired
    ScumApi scumApi;

    @Autowired
    HistoryApi historyApi;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homepage(ModelMap model)
    {
        List<Scum> scumList = scumApi.findAll();
        model.addAttribute("scumList", scumList);

        List<HistoryDto> historyDtoList = historyApi.retrieveHistory();
        model.addAttribute("historyList", historyDtoList);

        return new ModelAndView("homepage");
    }
}
