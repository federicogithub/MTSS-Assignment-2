////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.ItemType;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import java.util.List;

public class BillImpl implements Bill {

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        double total = 0;
        double min = 10000;
        double minM = 10000;
        int processori = 0;
        int mouse = 0;


        if(itemsOrdered == null) {
            throw new BillException("Lista nulla");
        }

        if(itemsOrdered.isEmpty()) {
            throw new BillException("Lista ordini vuota");
        }

        for (EItem item : itemsOrdered) { 
            double current = item.getPrice();
            if(item.getType().equals(ItemType.Processor)) {
                processori++;
            }
            if(item.getType().equals(ItemType.Mouse)) {
                mouse++;
            }
            if(current<minM && item.getType().equals(ItemType.Mouse) ) {
                minM=current;
            }
            if(current<min && item.getType().equals(ItemType.Processor) ) {
                min=current;
            }
            total += current;
        }

        if(processori > 5) {
            total -= 0.5*min;
        }

        if(mouse > 10) {
            total -= minM;
        }

        return total;
    }
}