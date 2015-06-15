package main.java.pl.edu.agh.toik.database.service;

import main.java.pl.edu.agh.toik.database.model.Section;
import main.java.pl.edu.agh.toik.database.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public Iterable<Section> findAllSections() {
        return sectionRepository.findAll();
    }

    public void saveSection(Section section) {
        sectionRepository.save(section);
    }

    public void saveSections(Iterable<Section> sections) {
        sectionRepository.save(sections);
    }

}
