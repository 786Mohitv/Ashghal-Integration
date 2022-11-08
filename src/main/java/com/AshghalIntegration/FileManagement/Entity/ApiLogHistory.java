package com.AshghalIntegration.FileManagement.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApiLogHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date requestTimeStamp;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date responseTimeStamp;

  @Column(nullable = false)
  private String ApiRefId;

  @Column(nullable = false)
  private String logFile;


}
