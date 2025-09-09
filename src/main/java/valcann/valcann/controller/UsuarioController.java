package valcann.valcann.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valcann.valcann.model.Usuario;
import valcann.valcann.pagination.Pagination;
import valcann.valcann.response.UsuarioResponse;
import valcann.valcann.service.UsuarioService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Value("${pagination.default:10}")
    private int defaultPageSize;

    @Value("${pagination.max:50}")
    private int maxPageSize;

    @GetMapping
    public ResponseEntity<UsuarioResponse> getUsuarios(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer page_size,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Boolean is_active,
            @RequestParam(defaultValue = "asc") String sort
    ) {
        int pageSize = (page_size == null) ? defaultPageSize : Math.min(page_size, maxPageSize);

        List<Usuario> allFiltered = usuarioService.filtrarUsuarios(q, role, is_active, sort);
        int total = allFiltered.size();

        int fromIndex = Math.max((page - 1) * pageSize, 0);
        int toIndex = Math.min(fromIndex + pageSize, total);

        List<Usuario> paged = (fromIndex >= total) ? List.of() : allFiltered.subList(fromIndex, toIndex);

        Pagination pagination = new Pagination(page, pageSize, total);
        UsuarioResponse response = new UsuarioResponse(paged, pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
