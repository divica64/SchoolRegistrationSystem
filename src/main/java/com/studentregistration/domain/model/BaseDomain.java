package com.studentregistration.domain.model;

import com.fasterxml.uuid.Generators;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Represents all common properties of standard domain objects
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private String rrn;
    @CreationTimestamp
    protected Date createdOn = new Date();

    @UpdateTimestamp
    protected Date modifiedOn;

    @PrePersist
    public void setIdx(){
        rrn= Generators.timeBasedGenerator().generate().toString();
    }



}
