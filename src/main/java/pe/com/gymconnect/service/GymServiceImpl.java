package pe.com.gymconnect.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gymconnect.common.BusinessException;
import pe.com.gymconnect.dto.CreateGymCommand;
import pe.com.gymconnect.dto.CreateGymResult;
import pe.com.gymconnect.dto.DeleteGymCommand;
import pe.com.gymconnect.dto.GetGymByIdQuery;
import pe.com.gymconnect.dto.GetGymByIdResult;
import pe.com.gymconnect.dto.GymDto;
import pe.com.gymconnect.dto.UpdateGymCommandWithId;
import pe.com.gymconnect.entity.Gym;
import pe.com.gymconnect.repository.GymRepository;

@Service
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public CompletableFuture<List<GymDto>> findAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            var gyms = gymRepository.findAll();
            List<GymDto> gymDtos = gyms.stream().map(gym -> new GymDto(gym.getId(), gym.getCode(), gym.getName(),
                    gym.getAddress(), gym.getPhone(), gym.getCreationDate())).toList();
            return gymDtos;
        });
    }

    @Async("virtualThreadExecutor")
    @Override
    public CompletableFuture<Page<GymDto>> findAllPaginatedAsync(Pageable pageable) {
        return CompletableFuture.supplyAsync(() -> {
            var gyms = gymRepository.findAll(pageable);
            Page<GymDto> gymDtos = gyms.map(gym -> new GymDto(gym.getId(), gym.getCode(), gym.getName(),
                    gym.getAddress(), gym.getPhone(), gym.getCreationDate()));
            return gymDtos;
        });
    }

    @Async("virtualThreadExecutor")
    @Override
    public CompletableFuture<GetGymByIdResult> singleAsync(GetGymByIdQuery query) {
        return CompletableFuture.supplyAsync(() -> {
            var gym = gymRepository.findById(query.id())
                    .orElseThrow(() -> new BusinessException("El id no existe", HttpStatus.BAD_REQUEST));

            return new GetGymByIdResult(gym.getId(), gym.getCode(), gym.getName(), gym.getAddress(), gym.getPhone(),
                    gym.getCreationDate());
        });
    }

    @Async("virtualThreadExecutor")
    @Transactional
    @Override
    public CompletableFuture<CreateGymResult> createAsync(CreateGymCommand command) {

        return CompletableFuture.supplyAsync(() -> {
            var gym = new Gym();
            boolean existName = gymRepository.existsByName(gym.getName());
            if (existName)
                throw new BusinessException("El nombre del gym ya existe", HttpStatus.CONFLICT);

            gym.setCode(gymRepository.generarCodigo());
            gym.setName(command.name());
            gym.setAddress(command.address());
            gym.setPhone(command.phone());
            gym.setCreationDate(new Date());

            gymRepository.save(gym);

            return new CreateGymResult(gym.getId(), gym.getCode(), gym.getName(), gym.getAddress(), gym.getPhone(),
                    gym.getCreationDate());
        });
    }

    @Async("virtualThreadExecutor")
    @Transactional
    @Override
    public CompletableFuture<Void> updateAsync(UpdateGymCommandWithId commandWithId) {
        return CompletableFuture.runAsync(() -> {
            var command = commandWithId.command();
            var id = commandWithId.id();

            var gym = gymRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("El gym no existe", HttpStatus.BAD_REQUEST));

            gym.setName(command.name());
            gym.setAddress(command.address());
            gym.setPhone(command.phone());

            gymRepository.save(gym);
        });
    }

    @Async("virtualThreadExecutor")
    @Override
    public CompletableFuture<Void> deleteAsync(DeleteGymCommand command) {
        return CompletableFuture.runAsync(() -> {
            var gym = gymRepository.findById(command.id())
                    .orElseThrow(() -> new BusinessException("El gym no existe", HttpStatus.BAD_REQUEST));
            gymRepository.deleteById(gym.getId());
        });
    }

}
