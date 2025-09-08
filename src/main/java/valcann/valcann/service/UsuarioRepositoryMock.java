package valcann.valcann.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import valcann.valcann.model.Usuario;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Configuration
public class UsuarioRepositoryMock {
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("../../resources/mock-users.json");
        usuarios = Arrays.asList(mapper.readValue(is, Usuario[].class));
    }

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Usuario findById(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
