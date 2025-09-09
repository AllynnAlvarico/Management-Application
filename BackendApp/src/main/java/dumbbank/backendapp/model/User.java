package dumbbank.backendapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userID")
    private String userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "username", nullable = false)
    private String password;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public String getUserId() { return this.userId; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}