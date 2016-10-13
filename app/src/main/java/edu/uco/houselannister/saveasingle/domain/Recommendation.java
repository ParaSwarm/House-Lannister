package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;

public class Recommendation  implements Serializable {

    private User friend;

    private User recommended;

    private String testimonial;

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getRecommended() {
        return recommended;
    }

    public void setRecommended(User recommended) {
        this.recommended = recommended;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }
}