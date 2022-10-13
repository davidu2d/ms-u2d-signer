package com.u2d.signer.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
public class PayloadRequest implements Serializable {

    private static final long serialVersionUID = 7065062851446686612L;

    @NotBlank
    @Schema(description = "Chave Pix", example = "+5561986460330", required = true)
    private String pixKey;

    @Schema(description = "Descrição do Pagamento", example = "Mensalidade do Inglês")
    private String description;

    @Schema(description = "Nome do titular da conta de recebimento", example = "Fulano de tal")
    private String merchantName;

    @Schema(description = "Cidade do titular da conta de recebimento", example = "Brasília")
    private String merchantCity;

    @Schema(description = "Id da transação", example = "2b0d7b3dcb6d")
    private String txId;

    @Schema(description = "Valor da transação com ponto e duas casas decimais", example = "100.00")
    private String amount;
}
