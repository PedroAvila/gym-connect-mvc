package pe.com.gymconnect.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.com.gymconnect.dto.CreateGoalCommand;
import pe.com.gymconnect.dto.CreateGoalResult;
import pe.com.gymconnect.dto.GetGoalByIdQuery;
import pe.com.gymconnect.dto.GetGoalByIdResult;
import pe.com.gymconnect.dto.GoalDto;
import pe.com.gymconnect.dto.UpdateGoalCommandWithId;

public interface GoalService {

    CompletableFuture<Page<GoalDto>> findAllAsync(Pageable pageable);

    CompletableFuture<GetGoalByIdResult> singleAsync(GetGoalByIdQuery query);

    CompletableFuture<CreateGoalResult> createAsync(CreateGoalCommand command);

    CompletableFuture<Void> updateAsync(UpdateGoalCommandWithId commandWithId);

}
