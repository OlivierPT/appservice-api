package org.olivierpt.appservice.api.application;

import org.springframework.web.bind.annotation.*;

@RestController()
public class ApplicationController {

    @RequestMapping(value = "/app",method = RequestMethod.POST)
    public Application createApplication(
            @RequestHeader(value = "X-Authent") String user,
            @RequestBody String body) {
        System.out.println(body);
        return new Application(body, body, user);
    }
}
