package com.in28min.camelmicroservicea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MatueDto {
    private String nome;
    private String apelido;
    private String status;
}
