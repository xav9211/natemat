package main.java.pl.edu.agh.toik.database.repository;

import main.java.pl.edu.agh.toik.database.model.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, String> {
}
