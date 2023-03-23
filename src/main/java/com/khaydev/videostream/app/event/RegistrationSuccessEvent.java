package com.khaydev.videostream.app.event;

import org.springframework.context.ApplicationEvent;

public class RegistrationSuccessEvent extends ApplicationEvent {

    public RegistrationSuccessEvent(Object source) {
        super(source);
    }
}
