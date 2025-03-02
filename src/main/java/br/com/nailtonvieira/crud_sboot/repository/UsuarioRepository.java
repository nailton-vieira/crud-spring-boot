package br.com.nailtonvieira.crud_sboot.repository;

import br.com.nailtonvieira.crud_sboot.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository <Usuario, Long>  {

}
