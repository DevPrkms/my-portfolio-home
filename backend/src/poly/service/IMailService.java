package poly.service;

import poly.dto.MailDTO;

public interface IMailService {

    int sendEmail(MailDTO pDTO);
}
