package by.dma.springboottesting.layers.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import by.dma.springboottesting.domains.customer.CustomerNotFoundException;
import by.dma.springboottesting.layers.model.Question;
import by.dma.springboottesting.layers.repository.QuestionRepository;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }

    public Question getQuestionById(Long id) {
        return repository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Question getRandomQuestion() {
        final var all = repository.findAll();
        final var randomIndex = ThreadLocalRandom.current().nextInt(all.size());
        return Optional.ofNullable(all.get(randomIndex)).orElseThrow(CustomerNotFoundException::new);
    }
}
