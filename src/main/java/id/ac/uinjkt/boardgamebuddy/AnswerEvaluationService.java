package id.ac.uinjkt.boardgamebuddy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.evaluation.FactCheckingEvaluator;
import org.springframework.ai.chat.evaluation.RelevancyEvaluator;
import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.stereotype.Service;

@Service
public class AnswerEvaluationService {

    private final RelevancyEvaluator relevancyEvaluator;
    private final FactCheckingEvaluator factCheckingEvaluator;

    public AnswerEvaluationService(ChatClient.Builder chatClientBuilder) {
        this.relevancyEvaluator = new RelevancyEvaluator(chatClientBuilder);
        this.factCheckingEvaluator = new FactCheckingEvaluator(chatClientBuilder);
    }

    public EvaluationResult checkRelevancy(String question, String answer) {
        var result = relevancyEvaluator.evaluate(new EvaluationRequest(question, answer));
        return new EvaluationResult("relevansi", result.isPass(), question, answer);
    }

    public EvaluationResult checkAccuracy(String question, String answer) {
        var result = factCheckingEvaluator.evaluate(new EvaluationRequest(question, answer));
        return new EvaluationResult("akurasi fakta", result.isPass(), question, answer);
    }
}
