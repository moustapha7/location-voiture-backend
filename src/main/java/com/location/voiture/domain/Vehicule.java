package com.location.voiture.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Vehicule.
 */
@Entity
@Table(name = "vehicule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vehicule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "matricule", nullable = false)
    private String matricule;

    @NotNull
    @Column(name = "modele", nullable = false)
    private String modele;

    @NotNull
    @Column(name = "marque", nullable = false)
    private String marque;

    @NotNull
    @Column(name = "prix", nullable = false)
    private Long prix;

    @Column(name = "image")
    private String image;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public Vehicule matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getModele() {
        return modele;
    }

    public Vehicule modele(String modele) {
        this.modele = modele;
        return this;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public Vehicule marque(String marque) {
        this.marque = marque;
        return this;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Long getPrix() {
        return prix;
    }

    public Vehicule prix(Long prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public Vehicule image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicule)) {
            return false;
        }
        return id != null && id.equals(((Vehicule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehicule{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", modele='" + getModele() + "'" +
            ", marque='" + getMarque() + "'" +
            ", prix=" + getPrix() +
            ", image='" + getImage() + "'" +
            "}";
    }
}
