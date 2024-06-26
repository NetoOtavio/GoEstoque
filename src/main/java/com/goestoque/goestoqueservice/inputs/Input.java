package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "inp_inputs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inp_id")
    private String id;

    @CreationTimestamp
    @Column(name = "inp_date",
            nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_id",
                nullable = false)
    private User user;

    /*@OneToMany(mappedBy = "input")
    private Set<InputItem> inputItems;*/
}
