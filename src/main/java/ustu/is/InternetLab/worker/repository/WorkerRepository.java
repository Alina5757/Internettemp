package ustu.is.InternetLab.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ustu.is.InternetLab.worker.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
