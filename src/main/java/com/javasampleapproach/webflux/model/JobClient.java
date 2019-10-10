package com.javasampleapproach.webflux.model;

import java.util.List;

public class JobClient {

    private long jobId;
    private String title;
    private String levelofhappiness;
    private boolean isitmanager;

    public JobClient(){}

    public JobClient(long jobId, String title, String levelofhappiness, boolean isitmanager){
        this.jobId = jobId;
        this.title = title;
        this.levelofhappiness = levelofhappiness;
        this.isitmanager = isitmanager;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevelofhappiness() {
        return levelofhappiness;
    }

    public void setLevelofhappiness(String levelofhappiness) {
        this.levelofhappiness = levelofhappiness;
    }

    public boolean isIsitmanager() {
        return isitmanager;
    }

    public void setIsitmanager(boolean isitmanager) {
        this.isitmanager = isitmanager;
    }

    @Override
    public String toString() {
        String infojob = String.format("jobId = %d, title = %s, levelofhappiness = %s, isitmanager = %d", jobId, title, levelofhappiness,isitmanager);
        return infojob;
    }
}
