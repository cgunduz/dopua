package com.cemgunduz.dopua.session;

import com.cemgunduz.dopua.domain.scum.model.Scum;
import com.cemgunduz.dopua.domain.scum.model.ScumFactory;
import com.cemgunduz.dopua.domain.scum.persistence.ScumDao;
import com.cemgunduz.dopua.domain.scum.persistence.ScumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by cgunduz on 3/24/2016.
 */

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ScumSession {

    private static final Logger logger = Logger.getLogger("Scum session initiater");

    private Scum self;
    private Date onlineAt;

    @Autowired
    ScumDao scumDao;

    @PostConstruct
    public void init()
    {
        onlineAt = new Date();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        List<ScumDto> onlineUser = scumDao.findByUsername(name);
        if(onlineUser == null || onlineUser.isEmpty())
        {
            logger.severe("Session init problem due to bug in authentication for name : ".concat(name));
            throw new IllegalAccessError("User not permitted to proceed");
        }

        if(onlineUser.size() > 1)
        {
            logger.severe("Same username used by multiple accounts ! Name : ".concat(name));
            throw new IllegalAccessError("User not permitted to proceed");
        }

        self = ScumFactory.fromDto(onlineUser.get(0));
    }

    public Integer getUserId()
    {
        return self.getId();
    }

    public Date getOnlineSince()
    {
        return new Date(onlineAt.getTime());
    }
}
