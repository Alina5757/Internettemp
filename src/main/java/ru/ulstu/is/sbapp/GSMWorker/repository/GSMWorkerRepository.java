package ru.ulstu.is.sbapp.GSMWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.GSMWorker.model.GSMWorker;

public interface GSMWorkerRepository extends JpaRepository<GSMWorker, Long> {
}