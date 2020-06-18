package com.gruzini.tennistico.events;

import com.gruzini.tennistico.models.dto.CreatedMatchDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateMatchEvent extends ApplicationEvent {

    private final CreatedMatchDto matchDto;
    private final String username;

    public CreateMatchEvent(Object source, CreatedMatchDto matchDto, String username) {
        super(source);
        this.matchDto = matchDto;
        this.username = username;
    }
}
