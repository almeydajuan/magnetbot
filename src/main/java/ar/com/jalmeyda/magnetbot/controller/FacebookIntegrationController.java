package ar.com.jalmeyda.magnetbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Juan Almeyda on 7/4/2016.
 */
@RestController
public class FacebookIntegrationController {

    private static final String VALIDATION_TOKEN = "EAANeGQLckDoBAAQxsufSXijxEvZChK4iVrFGX4zL3VJCFctFb9mIUhC4DyEcm0vQ5qM1sK04p4ZAvZC9QK5zvqHc7HFpiGb4RzkEZBZA8skWKZB123ZCvIOa0tvMBaUuLOIsy1zvOtqQfZCSFSrq51Ir3ZCSb7niRFKirZBs2M1UtVQgZDZD";
    private Logger logger = LoggerFactory.getLogger(FacebookIntegrationController.class);


    @RequestMapping("/webhook")
    public String webHook(@RequestParam(name = "hub.mode", required = false) String mode,
                          @RequestParam(name = "hub.verify_token", required = false) String token,
                          @RequestParam(name = "hub.challenge", required = false, defaultValue = "empty") String challenge) {
        logger.info("********************************************************************");
        logger.info(mode);
        logger.info(token);
        logger.info(challenge);
        logger.info("********************************************************************");
        return challenge;
    }
}
