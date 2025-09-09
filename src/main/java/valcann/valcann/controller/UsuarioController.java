package valcann.valcann.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import valcann.valcann.model.Usuario;
import valcann.valcann.service.UsuarioRepositoryMock;

import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepositoryMock usuarioRepositoryMock;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioRepositoryMock.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepositoryMock.findById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
