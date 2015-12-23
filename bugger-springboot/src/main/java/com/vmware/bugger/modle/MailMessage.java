package com.vmware.bugger.modle;

/**
 * Created by yshichel on 12/23/15.
 */
public class MailMessage {
    private String recipient;
    private String message;

    public MailMessage(){
        this.recipient = "";
        this.message = "";
    }
    public MailMessage(String recipient, String message){
        this.recipient = recipient;
        this.message = message;
    }
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
