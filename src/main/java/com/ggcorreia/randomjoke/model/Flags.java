package com.ggcorreia.randomjoke.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flags {

    private boolean sexist;
    private boolean explicit;

    public Flags() {
    }

    public Flags(boolean sexist, boolean explicit) {
        this.sexist = sexist;
        this.explicit = explicit;
    }

    public boolean isSexist() {
        return sexist;
    }

    public boolean isNotSexist() {
        return !sexist;
    }

    public void setSexist(boolean sexist) {
        this.sexist = sexist;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public boolean isNotExplicit() {
        return !explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }
}
