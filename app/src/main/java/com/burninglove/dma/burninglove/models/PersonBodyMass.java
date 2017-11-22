package com.burninglove.dma.burninglove.models;

/**
 * Created by malik on 11/12/2017.
 */

public class PersonBodyMass {
    float height;
    float weight;

    private PersonBodyMass() {}

    public PersonBodyMass(float height, float weight) {
        this.height = height;
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


}
