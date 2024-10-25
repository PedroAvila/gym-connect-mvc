package pe.com.gymconnect.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gymconnect.common.BusinessException;
import pe.com.gymconnect.dto.CreateGoalCommand;
import pe.com.gymconnect.dto.CreateGoalResult;
import pe.com.gymconnect.dto.GetGoalByIdQuery;
import pe.com.gymconnect.dto.GetGoalByIdResult;
import pe.com.gymconnect.dto.GoalDto;
import pe.com.gymconnect.dto.UpdateGoalCommandWithId;
import pe.com.gymconnect.entity.Goal;
import pe.com.gymconnect.repository.GoalRepository;
import pe.com.gymconnect.repository.GymRepository;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GymRepository gymRepository;

    public GoalServiceImpl(GoalRepository goalRepository, GymRepository gymRepository) {
        this.goalRepository = goalRepository;
        this.gymRepository = gymRepository;
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Page<GoalDto>> findAllAsync(Pageable pageable) {
        return CompletableFuture.supplyAsync(() -> {
            var goals = goalRepository.findAll(pageable);
            if (goals == null || goals.isEmpty())
                goals = Page.empty(pageable);
            Page<GoalDto> goalDtos = goals
                    .map(goal -> new GoalDto(goal.getId(), goal.getGym().getId(), goal.getCode(), goal.getName()));
            return goalDtos;
        });
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<GetGoalByIdResult> singleAsync(GetGoalByIdQuery query) {
        return CompletableFuture.supplyAsync(() -> {
            var goal = goalRepository.findById(query.id())
                    .orElseThrow(
                            () -> new BusinessException("El id '%d' no existe", HttpStatus.BAD_REQUEST, query.id()));
            return new GetGoalByIdResult(goal.getId(), goal.getGym().getId(), goal.getCode(), goal.getName());
        });
    }

    @Async("asyncExecutor")
    @Transactional
    @Override
    public CompletableFuture<CreateGoalResult> createAsync(CreateGoalCommand command) {

        return CompletableFuture.supplyAsync(() -> {

            var gym = gymRepository.findById(command.gymId())
                    .orElseThrow(() -> new BusinessException("GymId '%d' does not exist", HttpStatus.BAD_REQUEST,
                            command.gymId()));

            var goal = new Goal();
            goal.setGym(gym);
            goal.setCode(goalRepository.generarCodigo(command.gymId()));
            goal.setName(command.name());

            boolean existName = goalRepository.existsByName(command.name());
            if (existName)
                throw new BusinessException("The goal with the name '%s' already exists", HttpStatus.CONFLICT,
                        command.name());

            goalRepository.save(goal);

            return new CreateGoalResult(goal.getId(), goal.getGym().getId(), goal.getCode(), goal.getName());
        });
    }

    @Async("asyncExecutor")
    @Transactional
    @Override
    public CompletableFuture<Void> updateAsync(UpdateGoalCommandWithId commandWithId) {
        return CompletableFuture.runAsync(() -> {
            var id = commandWithId.id();
            var command = commandWithId.command();

            var gym = gymRepository.findById(command.gymId())
                    .orElseThrow(() -> new BusinessException("GymId '%d' does not exist", HttpStatus.BAD_REQUEST,
                            command.gymId()));

            var goal = goalRepository.findById(id)
                    .orElseThrow(
                            () -> new BusinessException("El goal con id '%d' no existe", HttpStatus.BAD_REQUEST, id));
            goal.setGym(gym);
            goal.setName(command.name());

            goalRepository.save(goal);
        });
    }

}
