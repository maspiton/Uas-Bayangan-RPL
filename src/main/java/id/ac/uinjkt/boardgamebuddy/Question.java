package id.ac.uinjkt.boardgamebuddy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Question(
        @NotBlank(message = "Pertanyaan tidak boleh kosong")
        @Size(max = 500, message = "Pertanyaan maksimal 500 karakter")
        String question) {
}
