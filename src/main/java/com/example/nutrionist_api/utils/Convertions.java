package com.example.nutrionist_api.utils;

import java.time.ZonedDateTime;

public class Convertions {
    public static ZonedDateTime convertZonedDateTimeToMilliseconds(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }
        // Truncate to milliseconds by converting to LocalDateTime
        // and back to ZonedDateTime with the same zone
        return ZonedDateTime.of(
                zonedDateTime.toLocalDateTime().withNano(zonedDateTime.getNano() / 1_000_000 * 1_000_000),
                zonedDateTime.getZone());
    }
}
