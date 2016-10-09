package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public enum Status implements Serializable {
    SINGLE,
    MARRIED,
    COMPLICATED,
    WIDOWED,
    SEPARATED,
    DIVORCED,
    OPENRELATIONSHIP,
    CIVILUNION,
    DOMESTICPARTNERSHIP,
}