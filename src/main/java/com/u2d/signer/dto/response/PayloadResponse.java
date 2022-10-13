package com.u2d.signer.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PayloadResponse implements Serializable {
    private static final long serialVersionUID = -8003673610247187813L;

    @Schema(description = "Payload pix copia e cola", example = "00020126660014br.gov.bcb.pix0114+55619864603300226Pagamento do pedido 123456520400005303986540510.005802BR5914David Jeremias6008Brasilia6207050300151BD")
    private String payload;
}
