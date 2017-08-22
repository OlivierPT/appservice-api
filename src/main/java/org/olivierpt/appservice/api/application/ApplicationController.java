package org.olivierpt.appservice.api.application;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.amazonaws.regions.Regions.EU_WEST_1;

@RestController()
@RequestMapping("/apps")
public class ApplicationController {

    @RequestMapping(method = RequestMethod.POST)
    public Application createApplication(
            @RequestHeader(value = "X-Authent") String user,
            @RequestBody Application newApp) {

        AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
                .withRegion(EU_WEST_1)
                .build();

        HashMap<String, AttributeValue> item_values =
                new HashMap<String, AttributeValue>();

        item_values.put("appId", new AttributeValue(newApp.getId()));

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

        return newApp;
    }
}
