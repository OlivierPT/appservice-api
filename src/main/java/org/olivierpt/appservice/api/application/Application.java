package org.olivierpt.appservice.api.application;

public class Application {

    private String id;
    private String name;
    private String dockerImage;

    public Application() {
    }

    public Application(String id, String name, String dockerImage) {
        this.id = id;
        this.name = name;
        this.dockerImage = dockerImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

