package com.sesac.reuse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
