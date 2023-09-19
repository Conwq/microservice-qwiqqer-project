package com.example.qwiqqer.verificationsservice.service;

import com.example.qwiqqer.verificationsservice.model.dto.VerificationRequest;
import com.example.qwiqqer.verificationsservice.model.dto.VerificationResponse;

public interface VerificationService {

	VerificationResponse checkingForExistUser(VerificationRequest verificationRequest);
}
