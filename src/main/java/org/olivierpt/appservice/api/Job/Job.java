package org.olivierpt.appservice.api.job;

public class Job {

    private String id;
    private String appName;
    private Integer port;
    private String passwd;
    private String user;

    public Job(String id, String appName, Integer port, String passwd, String user) {
        this.id = id;
        this.appName = appName;
        this.port = port;
        this.passwd = passwd;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
