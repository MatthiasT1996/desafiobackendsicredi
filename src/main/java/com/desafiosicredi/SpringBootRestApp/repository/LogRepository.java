package com.desafiosicredi.SpringBootRestApp.repository;

import com.desafiosicredi.SpringBootRestApp.entity.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Integer> {
}
