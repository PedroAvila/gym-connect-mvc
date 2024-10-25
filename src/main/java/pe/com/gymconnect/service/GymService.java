package pe.com.gymconnect.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.com.gymconnect.dto.CreateGymCommand;
import pe.com.gymconnect.dto.CreateGymResult;
import pe.com.gymconnect.dto.DeleteGymCommand;
import pe.com.gymconnect.dto.GetGymByIdQuery;
import pe.com.gymconnect.dto.GetGymByIdResult;
import pe.com.gymconnect.dto.GymDto;
import pe.com.gymconnect.dto.UpdateGymCommandWithId;

public interface GymService {

    CompletableFuture<Page<GymDto>> findAllAsync(Pageable pageable);

    CompletableFuture<GetGymByIdResult> singleAsync(GetGymByIdQuery query);

    CompletableFuture<CreateGymResult> createAsync(CreateGymCommand command);

    CompletableFuture<Void> updateAsync(UpdateGymCommandWithId commandWithId);

    CompletableFuture<Void> deleteAsync(DeleteGymCommand command);

}
