package org.olivierpt.appservice.api.application;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.olivierpt.appservice.api.utils.Chrono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.amazonaws.regions.Regions.EU_WEST_1;

@RestController()
public class ApplicationController {

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @RequestMapping(value = "/app", method = RequestMethod.POST)
    public Application createApplication(
            @RequestHeader(value = "X-Authent") String user,
            @RequestBody Application newApp) {

        logger.debug("Start Create Application");
        Chrono chrono = new Chrono();
        chrono.start();

        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
                .withRegion(EU_WEST_1)
                .build();



        HashMap<String, AttributeValue> item_values =
                new HashMap<String, AttributeValue>();

        item_values.put("appId", new AttributeValue(newApp.getId()));

        try {
            item_values.put("application", new AttributeValue(new ObjectMapper().writeValueAsString(newApp)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            ddb.putItem("AppService.Application", item_values);
        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The table \"%s\" can't be found.\n", "AppService.Application");
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            System.exit(1);
        } catch (AmazonServiceException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        chrono.stop();
        logger.debug("End Create Application in {}ms", chrono.getDurationMs());

        return newApp;
    }

    @RequestMapping(value = "/app/{appId}", method = RequestMethod.DELETE)
    public void deleteApp( @RequestHeader(value = "X-Authent") String user,
                           @PathVariable String appId) {

        logger.debug("Start Delete Application");
        Chrono chrono = new Chrono();
        chrono.start();

        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
                .withRegion(EU_WEST_1)
                .build();

        HashMap<String, AttributeValue> item_values =
                new HashMap<String, AttributeValue>();
        item_values.put("appId", new AttributeValue(appId));

        try {
            ddb.deleteItem("AppService.Application", item_values);
        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The table \"%s\" can't be found.\n", "AppService.Application");
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
        } catch (AmazonServiceException e) {
            System.err.println(e.getMessage());
        }

        chrono.stop();
        logger.debug("End Delete Application in {}ms", chrono.getDurationMs());
    }
}
