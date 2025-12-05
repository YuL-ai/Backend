package com.usuarios.Demo.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "papas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PapaModel {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;
    private String apellido;
    private String rut;
    private LocalDate fechaNacimiento; //guardamos la fecha, no la edad para que no se edite manualmente la edad de los papás.
    private String nacionalidad;
    private String ocupacion;
    private String estadoCivil;
    private int numeroHijos;
    private String hobbies;
    private String tipoPapa;   
    private String lema;
    private String descripcion;
    private int precio;
    private String imagenURL;
    private String rol = "PAPA";   
    
    @Transient
    public Integer getEdad() {
        if (this.fechaNacimiento == null) return null;
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
        /*Transient es una anotación de JPA
         un atributo o método NO debe ser persistido en la base de datos, es decir, JPA lo ignorará y no lo guardará
         en la base de datos, solo existe en el objeto en memoria, es decir, solo vive en el objeto java mientras corre,
         se cierra el programa, se elimina el atributo y método.
         */
}
