package edu.uco.houselannister.saveasingle.domain;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.io.Serializable;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;

public class Message implements Serializable {

    private User to;

    private User from;

    private String subject;

    private String message;

    private Boolean isRead;

    private Boolean isDeletedBySender;

    private Boolean isDeletedByRecipient;

    private Message replyToMessage;

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

    public Message getReplyToMessage() {
        return this.replyToMessage;
    }

    public void setReplyToMessage(Message replyToMessage) {
        this.replyToMessage = replyToMessage;
    }

    public void send(Context context, NotificationManager notificationManager) {

        this.getFrom().getInteractions().getOutBox().add(this);
        this.getTo().getInteractions().getInBox().add(this);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setContentTitle("New Message Received")
                        .setContentText(this.getSubject())
                        .setSmallIcon(R.drawable.stylebutton);

        Intent resultIntent = new Intent(context, MainActivity.class);

        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.putExtra("Message", this);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(1, mBuilder.build());
    }

    public String toString() {
        return "From: " + this.getFrom().getName() + "    Subject: " + this.getSubject();
    }
}