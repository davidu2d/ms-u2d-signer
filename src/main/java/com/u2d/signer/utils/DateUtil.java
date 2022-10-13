package com.u2d.signer.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String ZONE_ID = "America/Sao_Paulo";
    private static final String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";

    public static String now() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
        LocalDateTime now = LocalDateTime.now().atZone(ZoneId.of(ZONE_ID)).toLocalDateTime();
        return now.format(formatter);
    }
}
