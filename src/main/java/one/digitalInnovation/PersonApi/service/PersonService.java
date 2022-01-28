package one.digitalInnovation.PersonApi.service;

import lombok.AllArgsConstructor;
import one.digitalInnovation.PersonApi.dto.MessageResponseDTO;
import one.digitalInnovation.PersonApi.dto.request.PersonDTO;
import one.digitalInnovation.PersonApi.entity.Person;
import one.digitalInnovation.PersonApi.exception.PersonNotFoundException;
import one.digitalInnovation.PersonApi.mapper.PersonMapper;
import one.digitalInnovation.PersonApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

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

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        personRepository.deleteById(id);
    }

}
