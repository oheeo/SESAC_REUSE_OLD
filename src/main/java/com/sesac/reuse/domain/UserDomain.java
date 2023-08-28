package com.sesac.reuse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// email, oauthType을 DB에 저장하기 위한 도메인
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class UserDomain {
    @Id
    @Column(name="email", columnDefinition="VARCHAR(100)", nullable=true)
    private String email;

    @Column(name="oauth_type", columnDefinition="VARCHAR(50)")
    private String oauthType;
}
