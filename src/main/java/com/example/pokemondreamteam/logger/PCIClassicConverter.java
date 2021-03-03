package com.example.pokemondreamteam.logger;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class PCIClassicConverter extends ClassicConverter {

    private static final Collection<MessageFormatter> FORMATTERS = getFormatters();
    private static final Boolean FORMAT = getFormatFlag();

    @Override
    public String convert(ILoggingEvent event) {
        final String message = event.getFormattedMessage();
        return formatMessage(message);
    }

    public static String formatMessage(String message) {
        if (FORMAT && Objects.nonNull(FORMATTERS)) {
            for (MessageFormatter formatter : FORMATTERS) {
                if (formatter.handle(message)) {
                    message = formatter.format(message);
                }
            }
        }

        return message;
    }

    private static Boolean getFormatFlag() {
        final String flag = System.getenv("PCI_FORMATTER");
        return Objects.nonNull(flag) ? Boolean.valueOf(flag) : Boolean.TRUE;
    }

    private static Collection<MessageFormatter> getFormatters() {
        return Arrays.asList(new MessageFormatter[]{
                PANMessageFormatter.newInstance()
        });
    }
}
