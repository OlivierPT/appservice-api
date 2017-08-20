package org.olivierpt.appservice.api.application;

public class Application {

    private Integer id;
    private String name;
    private String DockerImage;

    public Application(Integer id, String name, String dockerImage) {
        this.id = id;
        this.name = name;
        DockerImage = dockerImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDockerImage() {
        return DockerImage;
    }

    public void setDockerImage(String dockerImage) {
        DockerImage = dockerImage;
    }
}

