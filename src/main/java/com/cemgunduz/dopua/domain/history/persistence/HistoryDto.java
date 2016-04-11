package com.cemgunduz.dopua.domain.history.persistence;

/**
 * Created by cgunduz on 4/5/2016.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "History")
public class HistoryDto {

    @Id
    private String id;

    private Integer awarderId;
    private String awarderUsername;
    private Integer rewardedId;
    private String rewardedUsername;
    private Integer amount;
    private String reason;

    private Date createdDate;

    public HistoryDto() {}

    public HistoryDto(Integer awarderId, Integer rewardedId, Integer amount, String reason) {
        this.id = id;
        this.awarderId = awarderId;
        this.rewardedId = rewardedId;
        this.amount = amount;
        this.reason = reason;
        this.createdDate = new Date();
    }

    public String getAwarderUsername() {
        return awarderUsername;
    }

    public void setAwarderUsername(String awarderUsername) {
        this.awarderUsername = awarderUsername;
    }

    public String getRewardedUsername() {
        return rewardedUsername;
    }

    public void setRewardedUsername(String rewardedUsername) {
        this.rewardedUsername = rewardedUsername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAwarderId() {
        return awarderId;
    }

    public void setAwarderId(Integer awarderId) {
        this.awarderId = awarderId;
    }

    public Integer getRewardedId() {
        return rewardedId;
    }

    public void setRewardedId(Integer rewardedId) {
        this.rewardedId = rewardedId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
