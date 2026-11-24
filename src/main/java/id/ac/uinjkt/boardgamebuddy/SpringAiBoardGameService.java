package id.ac.uinjkt.boardgamebuddy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SpringAiBoardGameService implements BoardGameService {

    private final ChatClient chatClient;

    public SpringAiBoardGameService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public Answer askQuestion(Question question) {
        String response = chatClient.prompt()
                .user("Jawab pertanyaan tentang permainan papan berikut dengan ringkas dan jelas: "
                        + question.question())
                .call()
                .content();

        if (response == null || response.isBlank()) {
            throw new IllegalStateException("Model AI mengembalikan jawaban kosong");
        }
        return new Answer(response);
    }
}
