package valcann.valcann.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import valcann.valcann.model.Usuario;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryMock {
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/mock-users.json");
        usuarios = Arrays.asList(mapper.readValue(is, Usuario[].class));
    }

    public List<Usuario> findAllFiltered(String q, String role, Boolean isActive, String sort) {
        return usuarios.stream()
                .filter(u -> q == null || u.getName().toLowerCase().contains(q.toLowerCase())
                        || u.getEmail().toLowerCase().contains(q.toLowerCase()))
                .filter(u -> role == null || u.getRole().equalsIgnoreCase(role))
                .filter(u -> isActive == null || u.getIsActive().equals(isActive))
                .sorted("desc".equalsIgnoreCase(sort)
                        ? Comparator.comparing(Usuario::getId).reversed()
                        : Comparator.comparing(Usuario::getId))
                .collect(Collectors.toList());
    }

    public Usuario findById(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
