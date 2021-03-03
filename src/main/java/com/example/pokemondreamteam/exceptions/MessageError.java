package com.example.pokemondreamteam.exceptions;

import com.example.pokemondreamteam.logger.PCIClassicConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageError {

    private MessageSource messageSource;

    public MessageError(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ApiError create(String code, String... replacements) {
        return new ApiError(code,
                messageSource.getMessage(code, replacements, LocaleContextHolder.getLocale()));
    }

    @Getter
    @EqualsAndHashCode
    @ToString
    public static class ApiError {

        private String code;
        private String description;

        private ApiError(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return PCIClassicConverter.formatMessage(description);
        }
    }
}

