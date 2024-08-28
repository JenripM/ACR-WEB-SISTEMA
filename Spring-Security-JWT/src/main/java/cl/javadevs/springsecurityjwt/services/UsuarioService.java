package cl.javadevs.springsecurityjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.javadevs.springsecurityjwt.models.Roles;
import cl.javadevs.springsecurityjwt.models.Usuarios;
import cl.javadevs.springsecurityjwt.repositories.IRolesRepository;
import cl.javadevs.springsecurityjwt.repositories.IUsuariosRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyecta el PasswordEncoder
    @Autowired
    private IRolesRepository rolesRepository;
    @Autowired
    private IUsuariosRepository usuarioRepository;

    public List<Usuarios> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuarios> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuarios updateUsuario(Long id, Usuarios usuarioDetails) {

        Usuarios usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario not found"));

        // Actualizar los detalles del usuario
        usuario.setUsername(usuarioDetails.getUsername());
        
        // Codifica la nueva contraseña si se ha proporcionado
        if (usuarioDetails.getPassword() != null && !usuarioDetails.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDetails.getPassword()));
        }

        // Buscar el rol por id
        Long rolId = usuarioDetails.getRoles().get(0).getIdRole();
        Roles rol = rolesRepository.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Crear una nueva lista mutable y actualizar el rol del usuario
        List<Roles> updatedRoles = new ArrayList<>();
        updatedRoles.add(rol);
        usuario.setRoles(updatedRoles);

        // Guardar los cambios
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteRolesByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }
}
