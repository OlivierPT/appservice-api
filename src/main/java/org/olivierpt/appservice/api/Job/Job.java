package org.olivierpt.appservice.api.job;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="AppService.Job")
public class Job {

    private String jobId;
    private String appId;
    private String appVersion;
    private String exectuionParameters;
    private Integer port;
    private String passwd;
    private String user;

    public Job() {
    }

    public Job(String jobId, String appId, String appVersion, String exectuionParameters, Integer port, String passwd, String user) {
        this.jobId = jobId;
        this.appId = appId;
        this.appVersion = appVersion;
        this.exectuionParameters = exectuionParameters;
        this.port = port;
        this.passwd = passwd;
        this.user = user;
    }

    @DynamoDBHashKey(attributeName="jobId")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getExectuionParameters() {
        return exectuionParameters;
    }

    public void setExectuionParameters(String exectuionParameters) {
        this.exectuionParameters = exectuionParameters;
    }
}
