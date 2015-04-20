package com.example.boot.thymeleaf.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name")
})
@Entity
@Table(name = "ITEM")
public class Item extends AbstractBaseEntity {

    @NotNull
    @Size(min = 3, max = 45)
    @Column(name = "NAME", length = 45)
    private String name;

    @Column(name = "COUNT")
    private Long count;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Size(max = 255)
    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
