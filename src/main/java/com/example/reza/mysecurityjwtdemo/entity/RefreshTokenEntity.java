package com.example.reza.mysecurityjwtdemo.entity;


import javax.persistence.*;

import lombok.*;

@Table(name = "refreshtoken")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity userEntity;

  @Column(nullable = false, unique = true)
  private String token;

}
