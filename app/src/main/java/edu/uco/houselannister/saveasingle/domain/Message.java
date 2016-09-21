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


}