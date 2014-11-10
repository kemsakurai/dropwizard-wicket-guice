package net.troja.dropwizard.wicket;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class WicketConfiguration extends Configuration {
    private boolean isDeployment = false;

    @JsonProperty
    public boolean isDeployment() {
        return isDeployment;
    }

    @JsonProperty
    public void setDeployment(boolean isDeployment) {
        this.isDeployment = isDeployment;
    }
}
