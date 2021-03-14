package com.location.voiture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A OffreLocation.
 */
@Entity
@Table(name = "offre_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OffreLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "nbre_jours")
    private Long nbreJours;

    @Column(name = "prix")
    private Long prix;

    @ManyToOne
    @JsonIgnoreProperties(value = "offreLocations", allowSetters = true)
    private Vehicule vehicule;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public OffreLocation libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getNbreJours() {
        return nbreJours;
    }

    public OffreLocation nbreJours(Long nbreJours) {
        this.nbreJours = nbreJours;
        return this;
    }

    public void setNbreJours(Long nbreJours) {
        this.nbreJours = nbreJours;
    }

    public Long getPrix() {
        return prix;
    }

    public OffreLocation prix(Long prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public OffreLocation vehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
        return this;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OffreLocation)) {
            return false;
        }
        return id != null && id.equals(((OffreLocation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OffreLocation{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", nbreJours=" + getNbreJours() +
            ", prix=" + getPrix() +
            "}";
    }
}
