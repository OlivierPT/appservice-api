package org.olivierpt.appservice.api.application;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="AppService.Application")
public class Application {

    private String appId;
    private String name;
    private String dockerImage;

    public Application() {
    }

    public Application(String appId, String name, String dockerImage) {
        this.appId = appId;
        this.name = name;
        this.dockerImage = dockerImage;
    }

    @DynamoDBHashKey(attributeName="appId")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDockerImage() {
        return dockerImage;
    }

    public void setDockerImage(String dockerImage) {
        this.dockerImage = dockerImage;
    }
}

