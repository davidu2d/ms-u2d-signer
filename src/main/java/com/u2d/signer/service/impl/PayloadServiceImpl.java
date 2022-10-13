package com.u2d.signer.service.impl;

import com.u2d.signer.constants.PayloadConstants;
import com.u2d.signer.dto.request.PayloadRequest;
import com.u2d.signer.dto.response.PayloadResponse;
import com.u2d.signer.repository.PayloadRepository;
import com.u2d.signer.service.PayloadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class PayloadServiceImpl implements PayloadService {


    private PayloadRepository repository;
    @Autowired
    public PayloadServiceImpl(PayloadRepository repository) {
        this.repository = repository;
    }

    private static final String ID_COUNTRY_CODE = "BR";
    private static final String ID_TRANSACTION_CURRENCY = "986";
    private static final String ID_MERCHANT_CATEGORY_CODE = "0000";
    private static final String ID_PAYLOAD_FORMAT_INDICATOR = "01";
    private static final String SIZE_CRC16 = "04";
    private static final String ID_MERCHANT_ACCOUNT_INFORMATION_GUI = "br.gov.bcb.pix";

    public PayloadResponse createPayload(PayloadRequest payloadRequest) {
        return generatePayload(payloadRequest);
    }

    private PayloadResponse generatePayload(PayloadRequest payloadRequest) {
        String payload = generateValue(PayloadConstants.ID_PAYLOAD_FORMAT_INDICATOR, ID_PAYLOAD_FORMAT_INDICATOR)
                .concat(getMerchantAccountInformation(payloadRequest))
                .concat(generateValue(PayloadConstants.ID_MERCHANT_CATEGORY_CODE, ID_MERCHANT_CATEGORY_CODE))
                .concat(generateValue(PayloadConstants.ID_TRANSACTION_CURRENCY, ID_TRANSACTION_CURRENCY))
                .concat(StringUtils.isNotBlank(payloadRequest.getAmount())
                        ? generateValue(PayloadConstants.ID_TRANSACTION_AMOUNT, String.valueOf(payloadRequest.getAmount()))
                        : "")
                .concat(generateValue(PayloadConstants.ID_COUNTRY_CODE, ID_COUNTRY_CODE))
                .concat(generateValue(PayloadConstants.ID_MERCHANT_NAME, payloadRequest.getMerchantName()))
                .concat(generateValue(PayloadConstants.ID_MERCHANT_CITY, payloadRequest.getMerchantCity()))
                .concat(getAdditionalDataFieldTemplate(payloadRequest.getTxId()))
                .concat(PayloadConstants.ID_CRC16 + SIZE_CRC16);
        int crcRes = getCRC16(payload);
        return PayloadResponse.builder()
                .payload(payload.concat(Integer.toHexString(crcRes).toUpperCase()))
                .build();
    }

    private String generateValue(String id, String value) {
        String size = String.format("%02d", value.length());
        return id.concat(size).concat(value);
    }

    private String getMerchantAccountInformation(PayloadRequest payloadRequest) {
        String gui = generateValue(PayloadConstants.ID_MERCHANT_ACCOUNT_INFORMATION_GUI, ID_MERCHANT_ACCOUNT_INFORMATION_GUI);
        String key = generateValue(PayloadConstants.ID_MERCHANT_ACCOUNT_INFORMATION_KEY, payloadRequest.getPixKey());
        String description = StringUtils.isNotBlank(payloadRequest.getDescription())
                ? generateValue(PayloadConstants.ID_MERCHANT_ACCOUNT_INFORMATION_DESCRIPTION, payloadRequest.getDescription())
                : "";
        return generateValue(PayloadConstants.ID_MERCHANT_ACCOUNT_INFORMATION, gui.concat(key).concat(description));
    }

    private String getAdditionalDataFieldTemplate(String transactionId) {
        String txId = generateValue(PayloadConstants.ID_ADDITIONAL_DATA_FIELD_TEMPLATE_TXID, transactionId);
        return generateValue(PayloadConstants.ID_ADDITIONAL_DATA_FIELD_TEMPLATE, txId);
    }

    private int getCRC16(String payload) {
        byte[] buffer = payload.getBytes(StandardCharsets.UTF_8);
        return crc16(buffer);
    }

    private int crc16(byte[] buffer) {
        int wCRCin = 0xffff;
        int wCPoly = 0x1021;
        for (byte b : buffer) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((wCRCin >> 15 & 1) == 1);
                wCRCin <<= 1;
                if (c15 ^ bit) {
                    wCRCin ^= wCPoly;
                }
            }
        }
        wCRCin &= 0xffff;
        return wCRCin ^= 0x0000;
    }
}
