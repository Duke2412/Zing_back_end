package com.example.zingfakebackend.repository.f5token;

import com.example.zingfakebackend.exception.AppException;
import com.example.zingfakebackend.model.RefreshToken;
import com.example.zingfakebackend.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class RefreshTokenService {
      private final RefreshTokenRepository refreshTokenRepository;

      public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
            this.refreshTokenRepository = refreshTokenRepository;
      }

      public RefreshToken generateRefreshToken(RefreshToken refreshToken) {
            refreshToken.setCreatedDate(Instant.now());
            return refreshTokenRepository.save(refreshToken);
      }

      void validateRefreshToken(String token) {
            refreshTokenRepository.findByToken(token).orElseThrow(() -> new AppException("Invalid refresh " +
                    "token"));
      }

      public void deleteRefreshToken(String token) {
            refreshTokenRepository.deleteByToken(token);
      }
}
