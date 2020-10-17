package me.apjung.backend.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.apjung.backend.domain.Base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at=CURRNET_TIMESTAMP WHERE USER_ID=?")
@Where(clause = "deleted_at IS NULL")
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String mobile;

    private boolean isEmailAuth;
    private String emailAuthToken;

    @Builder
    public User(String email, String password, String name, String mobile, boolean isEmailAuth, String emailAuthToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobile = mobile;
        this.isEmailAuth = isEmailAuth;
        this.emailAuthToken = emailAuthToken;
    }
}
