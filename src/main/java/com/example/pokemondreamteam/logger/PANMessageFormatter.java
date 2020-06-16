package com.example.pokemondreamteam.logger;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PANMessageFormatter implements MessageFormatter {

    private static final String REGEX = "(\\d{8,19})";

    private PANMessageFormatter() {
    }

    public static PANMessageFormatter newInstance() {
        return new PANMessageFormatter();
    }

    @Override
    public boolean handle(String message) {
        final Matcher matcher = Pattern.compile(REGEX).matcher(message);
        return Objects.nonNull(message) && matcher.find();
    }

    @Override
    public String format(String message) {
        final Matcher matcher = Pattern.compile(REGEX).matcher(message);

        if (matcher.find()) {
            String pan = matcher.group(1);

            return message.replaceAll(REGEX, maskValue(pan));
        }

        return message;
    }

    private static String maskValue(String value) {
        String mask = null;

        if (Objects.nonNull(value) && value.length() != 0) {
            final int SIZE_PAN_START = 0;
            final int SIZE_PAN_MID = 6;
            final int SIZE_PAN_END = 4;

            StringBuffer stringBuilder = new StringBuffer();
            stringBuilder.append(value, SIZE_PAN_START, SIZE_PAN_MID);
            stringBuilder.append(value.substring(SIZE_PAN_END, value.length() - SIZE_PAN_END)
                    .replaceAll("[a-zA-Z0-9]", "*"));
            stringBuilder.append(value.substring(value.length() - SIZE_PAN_END));

            mask = stringBuilder.toString();
        }

        return mask;
    }
}
