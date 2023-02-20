package com.example.adminedificios.repository;

import com.example.adminedificios.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

//    @Query(value = "SELECT * FROM roles AS r WHERE UPPER(r.nombre) LIKE UPPER(CONCAT('%',:name,'%'))", nativeQuery = true) // Forma 1, native query
    List<Rol> findByNombreContainingIgnoreCase(String name); // Forma 2, JPQL
}
