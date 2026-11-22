package br.gov.sp.santos.semam.saneamento.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Contrato para armazenamento de fotos de chamados (Local em dev ou Supabase Storage em produção).
 */
public interface FotoStorageService {
    String salvarFoto(String protocolo, MultipartFile arquivo);
}
