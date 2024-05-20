package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.users.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Entity(name = "inp_inputs")
@Data
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inp_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "inp_date",
            nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_id",
                nullable = false)
    private User user;

    @OneToMany(mappedBy = "input")
    private Set<InputItem> inputItems;
}
