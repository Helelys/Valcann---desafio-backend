package valcann.valcann.response;

import valcann.valcann.model.Usuario;
import valcann.valcann.pagination.Pagination;

import java.util.List;

public record UsuarioResponse(List<Usuario> data, Pagination pagination) {}
