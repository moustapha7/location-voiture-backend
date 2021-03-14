package com.location.voiture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Reservation.
 */
@Entity
@Table(name = "reservation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_depart")
    private ZonedDateTime dateDepart;

    @Column(name = "date_retour")
    private ZonedDateTime dateRetour;

    @Column(name = "nbre_jours")
    private Long nbreJours;

    @Column(name = "client")
    private String client;

    @Column(name = "prix")
    private Long prix;

    @ManyToOne
    @JsonIgnoreProperties(value = "reservations", allowSetters = true)
    private Vehicule vehicule;

    @ManyToOne
    @JsonIgnoreProperties(value = "reservations", allowSetters = true)
    private StatusReservation statusReservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateDepart() {
        return dateDepart;
    }

    public Reservation dateDepart(ZonedDateTime dateDepart) {
        this.dateDepart = dateDepart;
        return this;
    }

    public void setDateDepart(ZonedDateTime dateDepart) {
        this.dateDepart = dateDepart;
    }

    public ZonedDateTime getDateRetour() {
        return dateRetour;
    }

    public Reservation dateRetour(ZonedDateTime dateRetour) {
        this.dateRetour = dateRetour;
        return this;
    }

    public void setDateRetour(ZonedDateTime dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Long getNbreJours() {
        return nbreJours;
    }

    public Reservation nbreJours(Long nbreJours) {
        this.nbreJours = nbreJours;
        return this;
    }

    public void setNbreJours(Long nbreJours) {
        this.nbreJours = nbreJours;
    }

    public String getClient() {
        return client;
    }

    public Reservation client(String client) {
        this.client = client;
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getPrix() {
        return prix;
    }

    public Reservation prix(Long prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public Reservation vehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
        return this;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public StatusReservation getStatusReservation() {
        return statusReservation;
    }

    public Reservation statusReservation(StatusReservation statusReservation) {
        this.statusReservation = statusReservation;
        return this;
    }

    public void setStatusReservation(StatusReservation statusReservation) {
        this.statusReservation = statusReservation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }
        return id != null && id.equals(((Reservation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Reservation{" +
            "id=" + getId() +
            ", dateDepart='" + getDateDepart() + "'" +
            ", dateRetour='" + getDateRetour() + "'" +
            ", nbreJours=" + getNbreJours() +
            ", client='" + getClient() + "'" +
            ", prix=" + getPrix() +
            "}";
    }
}
