package com.generator.core.design.pom;

import com.generator.core.design.pom.interfaces.IProfileDesign;

public class Profile implements IProfileDesign {
    private String profileId;
    private Boolean activeByDefault;

    public Profile(String profileId, Boolean activeByDefault) {
        this.profileId = profileId;
        this.activeByDefault = activeByDefault;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Boolean getActiveByDefault() {
        return activeByDefault;
    }

    public void setActiveByDefault(Boolean activeByDefault) {
        this.activeByDefault = activeByDefault;
    }

    @Override
    public String toString() {
        return this.toStringProfile(this);
    }

}
