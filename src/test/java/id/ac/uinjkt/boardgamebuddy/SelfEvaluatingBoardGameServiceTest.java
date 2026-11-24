package id.ac.uinjkt.boardgamebuddy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SelfEvaluatingBoardGameServiceTest {

    private final BoardGameService boardGameService = mock(BoardGameService.class);
    private final AnswerEvaluationService evaluationService = mock(AnswerEvaluationService.class);
    private final SelfEvaluatingBoardGameService service =
            new SelfEvaluatingBoardGameService(boardGameService, evaluationService);

    @Test
    void returnsAnswerWhenEvaluationPasses() {
        var question = new Question("Bagaimana cara menang di catur?");
        var answer = new Answer("Lindungi raja dan skakmat lawan.");
        when(boardGameService.askQuestion(question)).thenReturn(answer);
        when(evaluationService.checkRelevancy(question.question(), answer.answer()))
                .thenReturn(new EvaluationResult("relevansi", true, question.question(), answer.answer()));

        assertEquals(answer, service.askWithRelevancyCheck(question));
        verify(boardGameService).askQuestion(question);
    }

    @Test
    void retriesOnceThenReturnsSafeFallback() {
        var question = new Question("Apa itu permainan papan?");
        var answer = new Answer("Cuaca hari ini cerah.");
        when(boardGameService.askQuestion(question)).thenReturn(answer);
        when(evaluationService.checkRelevancy(question.question(), answer.answer()))
                .thenReturn(new EvaluationResult("relevansi", false, question.question(), answer.answer()));

        Answer result = service.askWithRelevancyCheck(question);

        assertEquals(
                "Maaf, saya belum dapat menghasilkan jawaban yang cukup relevan. Coba ubah pertanyaannya.",
                result.answer());
        verify(boardGameService, times(2)).askQuestion(question);
        verify(evaluationService, times(2))
                .checkRelevancy(question.question(), answer.answer());
    }
}
