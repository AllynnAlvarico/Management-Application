package dumbbank.backendapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dumbbank_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId", nullable = true, unique = true)
    private String userId;

    @Column(name = "user_email", nullable = false, unique = true)
    private String user_email;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {}

    public User(Long id, String userId, String user_email, String password) {
        this.id = id;
        this.userId = userId;
        this.user_email = user_email;
        this.password = password;
    }


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getUserId() { return this.userId; }

    public String getUser_email() {
        return user_email;
    }

    public String getPassword() {
        return password;
    }
}