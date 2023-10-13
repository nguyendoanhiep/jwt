package com.tom.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "voucher")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Voucher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "value")
    private Long value;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "user_create_id")
    private Long userCreateId;

    @Column(name = "voucher_start_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date voucherStartDate;

    @Column(name = "voucher_expiration_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date voucherExpirationDate;

    @Column(name = "create_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "modified_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedDate;
}
