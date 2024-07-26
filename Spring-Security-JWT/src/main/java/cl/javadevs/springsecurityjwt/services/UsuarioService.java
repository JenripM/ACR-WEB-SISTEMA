package cl.javadevs.springsecurityjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.javadevs.springsecurityjwt.models.Roles;
import cl.javadevs.springsecurityjwt.models.Usuarios;
import cl.javadevs.springsecurityjwt.repositories.IRolesRepository;
import cl.javadevs.springsecurityjwt.repositories.IUsuariosRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private PasswordEncoder passwordEncoder;
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

            usuario.setUsername(usuarioDetails.getUsername());
            // usuario.setPassword(passwordEncoder.encode(usuarioDetails.getPassword()));


            // String roleName = usuarioDetails.getRoles().get(0).getName();

            // Roles roles = rolesRepository.findByName(roleName).get();
            // usuario.setRoles(Collections.singletonList(roles));


        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteRolesByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }
}
