package edu.unimagdalena.api.model.mappers;
import edu.unimagdalena.api.model.dto_save.PaymentToSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.dto.PaymentDTO;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    //@Mapping(source = "orderId", target = "order.id")
    Payment paymentDtoToPayment(PaymentDTO paymentDTO);

    //@Mapping(source = "order.id", target = "orderId")
    PaymentDTO paymentToPaymentDto(Payment payment);

    @Mapping(target = "id", ignore = true)
    //@Mapping(source = "order.id", target = )
    Payment paymentToSaveDtoToPayment(PaymentToSaveDto paymentToSaveDto);

    PaymentToSaveDto paymentToPaymentToSaveDto(Payment payment);

}
