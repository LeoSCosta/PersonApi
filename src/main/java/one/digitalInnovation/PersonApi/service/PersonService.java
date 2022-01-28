package one.digitalInnovation.PersonApi.service;

import one.digitalInnovation.PersonApi.dto.MessageResponseDTO;
import one.digitalInnovation.PersonApi.dto.request.PersonDTO;
import one.digitalInnovation.PersonApi.entity.Person;
import one.digitalInnovation.PersonApi.mapper.PersonMapper;
import one.digitalInnovation.PersonApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSaved = personMapper.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSaved);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allpeople = personRepository.findAll();
        return allpeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
