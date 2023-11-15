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

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "value" )
    private Long value;

    @Column(name = "quantity" )
    private Long quantity;

    @Column(name = "status")
    private Integer status;

    @Column(name = "voucher_start_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date voucherStartDate;

    @Column(name = "voucher_expiration_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date voucherExpirationDate;

    @Column(name = "create_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createDate;

    @Column(name = "modified_date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedDate;
}
