package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public enum Relationship  implements Serializable {
    EXCLUSIVE,
    LONGTERM,
    SHORTTERM,
    DATING,
    SERIOUS,
    INTIMATE,
    PASSIONATE,
    PLATONIC
}