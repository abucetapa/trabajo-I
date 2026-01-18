package es.upsa.ssbbdd2.trabajo1.json.adapters;

import jakarta.json.bind.adapter.JsonbAdapter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeAdapter implements JsonbAdapter<LocalDateTime, Long> {
    @Override
    public Long adaptToJson(LocalDateTime date) {
        if (date == null) return null;
        return date.atZone(ZoneId.of("Europe/Madrid")).toEpochSecond();
    }

    @Override
    public LocalDateTime adaptFromJson(Long seconds) {
        if (seconds == null) return null;
        return Instant.ofEpochSecond(seconds)
                .atZone(ZoneId.of("Europe/Madrid"))
                .toLocalDateTime();
    }
}