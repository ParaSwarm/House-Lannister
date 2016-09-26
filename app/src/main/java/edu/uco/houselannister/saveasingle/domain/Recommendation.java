package edu.uco.houselannister.saveasingle.domain;

public class Recommendation {

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