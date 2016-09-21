package edu.uco.houselannister.saveasingle.domain;

/**
 * 
 */
public class Message {

    /**
     * Default constructor
     */
    public Message() {
    }

    /**
     * 
     */
    private User to;

    /**
     * 
     */
    private User from;

    /**
     * 
     */
    private String subject;

    /**
     * 
     */
    private String message;

    /**
     * 
     */
    private Boolean isRead;

    /**
     * 
     */
    private Boolean isDeletedBySender;

    /**
     * 
     */
    private Boolean isDeletedByRecipient;

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getDeletedBySender() {
        return isDeletedBySender;
    }

    public void setDeletedBySender(Boolean deletedBySender) {
        isDeletedBySender = deletedBySender;
    }

    public Boolean getDeletedByRecipient() {
        return isDeletedByRecipient;
    }

    public void setDeletedByRecipient(Boolean deletedByRecipient) {
        isDeletedByRecipient = deletedByRecipient;
    }
}