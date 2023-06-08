package com.alex.practice.CRUDpractice.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="persons")
public class Person implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="email", unique = true)
    @Email
    @NotEmpty(message = "Почта не должна быть пустой")
    private String email;
    @Column(name="password")
    @NotEmpty(message = "Пароль не должен быть пустым")
    //@Size(min = 5,max = 15,message = "Пароль")
    private String password;

    @Column(name="full_name")
    @NotEmpty(message = "ФИО не должно быть пустым")
    private String fullName;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private List<EntryTime> entryTimeList;

    public Person(String username, String password, String fullName, Date dateOfBirth, Role role) {
        this.email = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        entryTimeList = new ArrayList<EntryTime>();
    }

    //TODO: Добавление времени входа
    public void addEntryTimeToPerson(EntryTime et){
        entryTimeList.add(et);
    }

    public void addEntryTimeListToPerson(ArrayList<EntryTime> et){
        entryTimeList.addAll(et);
    }

    public void addCurrentEntryTime(){
        entryTimeList.add(new EntryTime());
    }



    // TODO:    Spring Security part
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
