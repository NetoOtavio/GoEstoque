package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.users.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity(name = "inp_inputs")
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inp_id")
    private UUID id;

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
