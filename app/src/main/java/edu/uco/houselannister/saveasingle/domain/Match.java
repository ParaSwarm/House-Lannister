package edu.uco.houselannister.saveasingle.domain;

import java.util.*;

public class Match {

    private User user;

    private Date matchDate;

    private Double overallMatchPercent;

    private Double personalityMatchPercent;

    private Double interestsMatchPercent;

    private Double relationshipMatchPercent;

    private Double demographicsMatchPercent;

    private Double qaMatchPercent;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Double getOverallMatchPercent() {
        return overallMatchPercent;
    }

    public void setOverallMatchPercent(Double overallMatchPercent) {
        this.overallMatchPercent = overallMatchPercent;
    }

    public Double getPersonalityMatchPercent() {
        return personalityMatchPercent;
    }

    public void setPersonalityMatchPercent(Double personalityMatchPercent) {
        this.personalityMatchPercent = personalityMatchPercent;
    }

    public Double getInterestsMatchPercent() {
        return interestsMatchPercent;
    }

    public void setInterestsMatchPercent(Double interestsMatchPercent) {
        this.interestsMatchPercent = interestsMatchPercent;
    }

    public Double getRelationshipMatchPercent() {
        return relationshipMatchPercent;
    }

    public void setRelationshipMatchPercent(Double relationshipMatchPercent) {
        this.relationshipMatchPercent = relationshipMatchPercent;
    }

    public Double getDemographicsMatchPercent() {
        return demographicsMatchPercent;
    }

    public void setDemographicsMatchPercent(Double demographicsMatchPercent) {
        this.demographicsMatchPercent = demographicsMatchPercent;
    }

    public Double getQaMatchPercent() {
        return qaMatchPercent;
    }

    public void setQaMatchPercent(Double qaMatchPercent) {
        this.qaMatchPercent = qaMatchPercent;
    }
}
