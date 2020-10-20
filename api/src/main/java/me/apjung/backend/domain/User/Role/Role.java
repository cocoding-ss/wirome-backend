package me.apjung.backend.domain.User.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.apjung.backend.domain.Base.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "roles")
@SQLDelete(sql = "UPDATE users SET deleted_at=CURRNET_TIMESTAMP WHERE `role_id`=?")
@Where(clause = "deleted_at IS NULL")
public class Role extends BaseEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Code code;
    private String description;

    @Transient
    private final String prefixAuthority = "ROLE_";

    @Override
    public String getAuthority() {
        return this.prefixAuthority + this.code.name();
    }
}
