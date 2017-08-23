package org.olivierpt.appservice.api.job;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
public class JobController {



    @RequestMapping(value = "/job",method = RequestMethod.GET)
    public Job getJob(@RequestHeader(value = "X-Authent", required = true) String user,
                        @RequestParam(value="appName", required = true) String appName) {
        return new Job(UUID.randomUUID().toString(), appName, 10020, "generatedPasswd", user);
    }
}
