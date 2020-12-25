package com.novko.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.orders.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "T_USERS")
public class User implements UserDetails {

    private static final long serialVersionUID = -2035549605233491952L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_orders_gen", sequenceName = "seq_orders", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orders_gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    private Long id;

    @Column(name = "USERNAME")
    @NotBlank
    @Email
    private String username;

    @Column(name = "PASSWORD")
    @NotBlank
    @JsonIgnore
    private String password;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "CODE")
    @NotBlank
    private String code;

    @Column(name = "ACTIVE")
    @JsonIgnore
    private boolean active;

    @Column(name = "ROLE")
    private String role;


    @Column(name = "RABAT")
    @NotNull
    private Double rabat;


    @Transient
    @JsonIgnore
    private List<Roles> roles;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private List<Order> orders;

    //informacije o kupcu (left header info in pdf)
    @Column(name = "FIRMA")
    @NotBlank
    private String firma;

    @Column(name = "ULICA")
    @NotBlank
    private String ulica;

    @Column(name = "GRAD")
    @NotBlank
    private String grad;

    @Column(name = "PIB")
    @NotBlank
    private String pib;

    @Column(name = "MB")
    @NotBlank
    private String mb;
    //end info o kupcu


    public User() {
    }

//    public void addOrder(Order order){
//        this.orders.add(order);
//        order.setUser(this);
//    }
//
//    public void removeOrder(Order order){
//        this.orders.remove(order);
//        order.setUser(null);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Double getRabat() {
        return rabat;
    }

    public void setRabat(Double rabat) {
        this.rabat = rabat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

//INFO O KUPCU
    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

//UserDetails overriden methods
    @Override
    @Transient
//    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    @Transient
//    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
//    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
//    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
//    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}
