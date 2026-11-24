package id.ac.uinjkt.boardgamebuddy;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskController {

    private final BoardGameService boardGameService;
    private final SelfEvaluatingBoardGameService selfEvaluatingService;

    public AskController(
            BoardGameService boardGameService,
            SelfEvaluatingBoardGameService selfEvaluatingService) {
        this.boardGameService = boardGameService;
        this.selfEvaluatingService = selfEvaluatingService;
    }

    @PostMapping("/ask")
    public Answer ask(@Valid @RequestBody Question question) {
        return boardGameService.askQuestion(question);
    }

    @PostMapping("/ask/verified")
    public Answer askWithRelevancyCheck(@Valid @RequestBody Question question) {
        return selfEvaluatingService.askWithRelevancyCheck(question);
    }
}
