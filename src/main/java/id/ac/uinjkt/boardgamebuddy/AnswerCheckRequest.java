package id.ac.uinjkt.boardgamebuddy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AnswerCheckRequest(
        @NotBlank(message = "Pertanyaan tidak boleh kosong")
        @Size(max = 500, message = "Pertanyaan maksimal 500 karakter")
        String question,
        @NotBlank(message = "Jawaban tidak boleh kosong")
        @Size(max = 8000, message = "Jawaban maksimal 8000 karakter")
        String answer) {
}
