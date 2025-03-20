package com.email.writer.Model;

import lombok.Data;

@Data
public class EmailRequest {

    private String emailContent;
    private String tone; // tone means how should be our reply like sarcastic, funny, professional

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

}
