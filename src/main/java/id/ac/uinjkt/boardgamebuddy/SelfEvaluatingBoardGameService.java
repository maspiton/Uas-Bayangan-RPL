package id.ac.uinjkt.boardgamebuddy;

import org.springframework.stereotype.Service;

@Service
public class SelfEvaluatingBoardGameService {

    private static final int MAX_ATTEMPTS = 2;
    private static final String FALLBACK_ANSWER =
            "Maaf, saya belum dapat menghasilkan jawaban yang cukup relevan. Coba ubah pertanyaannya.";

    private final BoardGameService boardGameService;
    private final AnswerEvaluationService evaluationService;

    public SelfEvaluatingBoardGameService(
            BoardGameService boardGameService,
            AnswerEvaluationService evaluationService) {
        this.boardGameService = boardGameService;
        this.evaluationService = evaluationService;
    }

    public Answer askWithRelevancyCheck(Question question) {
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            Answer answer = boardGameService.askQuestion(question);
            EvaluationResult evaluation =
                    evaluationService.checkRelevancy(question.question(), answer.answer());
            if (evaluation.passed()) {
                return answer;
            }
        }
        return new Answer(FALLBACK_ANSWER);
    }
}
