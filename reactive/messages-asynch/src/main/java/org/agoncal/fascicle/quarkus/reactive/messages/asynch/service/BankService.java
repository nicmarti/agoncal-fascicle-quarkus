package org.agoncal.fascicle.quarkus.reactive.messages.asynch.service;

import org.agoncal.fascicle.quarkus.reactive.messages.asynch.model.PurchaseOrder;
import org.agoncal.fascicle.quarkus.reactive.messages.asynch.model.Status;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BankService {

  private static final Logger LOGGER = Logger.getLogger(BankService.class);

  @Incoming("po-prepared")
  @Outgoing("bank-validated")
  public PurchaseOrder validate(PurchaseOrder po) {
    LOGGER.info("Validating Credit Card for PO: " + po.id);
    LOGGER.debug(po + "\n");

    if ((po.id & 1) == 0) {
      po.creditCard.status = Status.VALID;
    } else {
      po.creditCard.status = Status.INVALID;
    }
    return po;
  }
}
