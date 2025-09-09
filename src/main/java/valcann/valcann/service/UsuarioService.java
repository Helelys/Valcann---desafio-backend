package valcann.valcann.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import valcann.valcann.model.Usuario;
import valcann.valcann.repository.UsuarioRepositoryMock;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepositoryMock repo;

    public List<Usuario> filtrarUsuarios(String q, String role, Boolean isActive, String sort) {
        log.info("Filtrando usuários | q={}, role={}, is_active={}, sort={}", q, role, isActive, sort);
        return repo.findAllFiltered(q, role, isActive, sort);
    }

    public Usuario buscarPorId(Long id) {
        log.info("Buscando usuário por ID: {}", id);
        return repo.findById(id);
    }
}
