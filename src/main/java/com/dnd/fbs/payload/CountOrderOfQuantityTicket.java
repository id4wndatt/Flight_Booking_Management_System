package com.dnd.fbs.payload;

public class CountOrderOfQuantityTicket {
    private long quantityTicketPerOrder;
    private long quantitySameNumberOfTicketPerOrder;

    public long getQuantityTicketPerOrder() {
        return quantityTicketPerOrder;
    }

    public void setQuantityTicketPerOrder(long quantityTicketPerOrder) {
        this.quantityTicketPerOrder = quantityTicketPerOrder;
    }

    public long getQuantitySameNumberOfTicketPerOrder() {
        return quantitySameNumberOfTicketPerOrder;
    }

    public void setQuantitySameNumberOfTicketPerOrder(long quantitySameNumberOfTicketPerOrder) {
        this.quantitySameNumberOfTicketPerOrder = quantitySameNumberOfTicketPerOrder;
    }

    @Override
    public String toString() {
        return "CountOrderOfQuantityTicket{" +
                "quantityTicketPerOrder=" + quantityTicketPerOrder +
                ", quantitySameNumberOfTicketPerOrder=" + quantitySameNumberOfTicketPerOrder +
                '}';
    }
}
