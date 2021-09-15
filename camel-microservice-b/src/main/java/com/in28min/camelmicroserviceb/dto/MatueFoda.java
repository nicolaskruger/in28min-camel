package com.in28min.camelmicroserviceb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MatueFoda {
    private String nome;
    private String apelido;
    private String status;
}
