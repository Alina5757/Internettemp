package ru.ulstu.is.sbapp.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.worker.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
