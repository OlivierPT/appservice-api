package org.olivierpt.appservice.api.job;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.olivierpt.appservice.api.App;
import org.olivierpt.appservice.api.application.Application;
import org.olivierpt.appservice.api.utils.Chrono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

import static com.amazonaws.regions.Regions.EU_WEST_1;

@RestController()
public class JobController {

    Logger logger = LoggerFactory.getLogger(JobController.class);

    /**
     * Find a Job from its jobId
     * @param user
     * @param jobId
     * @return
     */
    @RequestMapping(value = "/job/{jobId}",method = RequestMethod.GET)
    public Job getJob(@RequestHeader(value = "X-Authent", required = true) String user,
                      @PathVariable String jobId) {

        logger.debug("START - Find a Job");
        Chrono chrono = new Chrono();
        chrono.start();

        AmazonDynamoDB dynamoDbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(EU_WEST_1)
                .build();

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDbClient);

        Job job = mapper.load(Job.class, jobId);

        chrono.stop();
        logger.debug("END - Find a Job in {}ms", chrono.getDurationMs());
        return job;
    }

    /**
     * Start a new Job for the job template in parameter
     * @param user
     * @param jobTemplate
     * @return
     */
    @RequestMapping(value = "/job",method = RequestMethod.POST)
    public Job startJob(@RequestHeader(value = "X-Authent") String user,
                        @RequestBody Job jobTemplate) {

        logger.debug("Start Launch a Job");
        Chrono chrono = new Chrono();
        chrono.start();

        AmazonDynamoDB dynamoDbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(EU_WEST_1)
                .build();

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDbClient);

        Application application = mapper.load(Application.class, jobTemplate.getAppId());

        jobTemplate.setJobId(String.valueOf(System.currentTimeMillis()));
        jobTemplate.setUser(user);
        jobTemplate.setPasswd(user+String.valueOf(System.currentTimeMillis()));
        Random randomGenerator = new Random();
        jobTemplate.setPort(10000+randomGenerator.nextInt(1000));

        mapper.save(jobTemplate);

        chrono.stop();
        logger.debug("End Launch a Job in {}ms", chrono.getDurationMs());

        return jobTemplate;
    }


}
