package ru.ulstu.is.sbapp.gsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.gsm.model.GSM;

public interface GSMRepository extends JpaRepository<GSM, Long>{
}