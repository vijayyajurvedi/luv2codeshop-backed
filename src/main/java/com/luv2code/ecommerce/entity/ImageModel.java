package com.luv2code.ecommerce.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="image_model")
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ImageModel {
    @Id
    @Column(name = "id")
    @GeneratedValue 
    private Long id;

    
    @Column(name = "name",unique = true)
    private String imagename;

    @Column(name = "type")
    private String type;

    @Lob 
    @Column(name = "pic")
    private byte[] pic;
    
    @OneToOne(  mappedBy = "imagename",cascade = CascadeType.ALL )
    private  Product products;

//Custom Constructor
    public ImageModel(String name, String type, byte[] pic) {
        this.imagename = name;
        this.type = type;
        this.pic = pic;
    }
}