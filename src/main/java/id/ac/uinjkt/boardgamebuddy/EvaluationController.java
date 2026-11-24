package id.ac.uinjkt.boardgamebuddy;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationController {

    private final AnswerEvaluationService evaluationService;

    public EvaluationController(AnswerEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate/relevancy")
    public EvaluationResult checkRelevancy(@Valid @RequestBody AnswerCheckRequest request) {
        return evaluationService.checkRelevancy(request.question(), request.answer());
    }

    @PostMapping("/evaluate/accuracy")
    public EvaluationResult checkAccuracy(@Valid @RequestBody AnswerCheckRequest request) {
        return evaluationService.checkAccuracy(request.question(), request.answer());
    }
}
