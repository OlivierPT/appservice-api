package org.olivierpt.appservice.api.application;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.olivierpt.appservice.api.utils.Chrono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.amazonaws.regions.Regions.EU_WEST_1;

@RestController()
public class ApplicationController {

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    /**
     * Create an application configuration
     *
     * @param user
     * @param newApp
     * @return
     */
    @RequestMapping(value = "/app", method = RequestMethod.POST)
    public Application createApplication(
            @RequestHeader(value = "X-Authent") String user,
            @RequestBody Application newApp) {

        logger.debug("Start Create Application");
        Chrono chrono = new Chrono();
        chrono.start();

        AmazonDynamoDB dynamoDbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(EU_WEST_1)
                .build();

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDbClient);

        mapper.save(newApp);


        chrono.stop();
        logger.debug("End Create Application in {}ms", chrono.getDurationMs());

        return newApp;
    }

    /**
     * Delete an Application configuration
     *
     * @param user
     * @param appId
     */
    @RequestMapping(value = "/app/{appId}", method = RequestMethod.DELETE)
    public void deleteApp( @RequestHeader(value = "X-Authent") String user,
                           @PathVariable String appId) {

        logger.debug("Start Delete Application");
        Chrono chrono = new Chrono();
        chrono.start();

        AmazonDynamoDB dynamoDbClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(EU_WEST_1)
                .build();

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDbClient);

        Application application = new Application();
        application.setAppId(appId);

        mapper.delete(application);

        chrono.stop();
        logger.debug("End Delete Application in {}ms", chrono.getDurationMs());
    }
}
