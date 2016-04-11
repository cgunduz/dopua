package com.cemgunduz.dopua.domain.scum.controller.input;

/**
 * Created by cgunduz on 3/24/2016.
 */
public class DopuaCreationInput {

    private Integer awarderId;
    private Integer rewardedId;
    private Integer amount;
    private String reason;

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

    @Override
    public String toString() {
        return "DopuaCreationInput{" +
                "awarderId=" + awarderId +
                ", rewardedId=" + rewardedId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                '}';
    }

    public Boolean isEmpty()
    {
        return awarderId == null || rewardedId == null || amount == null || reason == null || reason.isEmpty();
    }
}
