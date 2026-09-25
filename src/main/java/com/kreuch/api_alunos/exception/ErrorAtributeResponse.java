package com.kreuch.api_alunos.exception;

import java.time.Instant;
import java.util.List;

public record ErrorAtributeResponse(
        int status,
        String message,
        Instant timestamp,
        List<ErrorAtribute> errorsAtribute
) {
}
