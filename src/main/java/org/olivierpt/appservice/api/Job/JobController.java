package org.olivierpt.appservice.api.Job;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
public class JobController {



    @RequestMapping(value = "/job",method = RequestMethod.GET)
    public Job greeting(@RequestHeader(value = "X-Authent", required = true) String user,
                        @RequestParam(value="appName", required = true) String appName) {
        return new Job(UUID.randomUUID().toString(), appName, 10020, "generatedPasswd", user);
    }
}
