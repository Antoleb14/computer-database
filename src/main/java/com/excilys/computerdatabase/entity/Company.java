package com.excilys.computerdatabase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import com.excilys.computerdatabase.rest.View;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Company Entity.
 *
 * @author excilys
 *
 */
@Entity
@Table(name = "company")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.Summary.class)
    private Long id;

    @Column(name = "name")
    @JsonView(View.Summary.class)
    private String name;

    /**
     * Default Constructor.
     */
    public Company() {
    }

    /**
     * Constructor of the class.
     *
     * @param l
     *            Id
     * @param name
     *            Name of the company
     */
    public Company(Long l, String name) {
        this.id = l;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String res = id + " - ";
        res += name;
        return res;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Company other = (Company) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
