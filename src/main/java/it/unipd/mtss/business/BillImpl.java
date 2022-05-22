////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.ItemType;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class BillImpl implements Bill {

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {

        double total = 0;
        double min = 10000;
        double minM = 10000;
        double minMT = 10000;
        int processori = 0;
        int mouse = 0;
        int tastiere = 0;

        if(itemsOrdered == null) {
            throw new BillException("Lista nulla");
        }

        if(itemsOrdered.isEmpty()) {
            throw new BillException("Lista ordini vuota");
        }

        if(itemsOrdered.size() > 30) {
            throw new BillException("Limite ordine superato");
        }

        for (EItem item : itemsOrdered) {
            double current = item.getPrice();
            if(item.getType().equals(ItemType.Processor)) {
                processori++;
            }
            if(item.getType().equals(ItemType.Mouse)) {
                mouse++;
            }
            if(item.getType().equals(ItemType.Keyboard)) {
                tastiere++;
            }
            if((current<=minM) && item.getType().equals(ItemType.Mouse) ) {
                minM=current;
            }
            if((current<=min) && item.getType().equals(ItemType.Processor) ) {
                min=current;
            }
            if((current<=minMT) && (item.getType().equals(ItemType.Mouse) || item.getType().equals(ItemType.Keyboard))) {
                minMT=current;
            }
            total += current;
        }

        if(processori > 5) {
            total -= 0.5*min;
        }

        if(mouse > 10) {
            total -= minM;
        }

        if((mouse == tastiere) && (mouse != 0)) {
            total -= minMT;
        }

        if(total>1000) {
            total -= total*0.1;
        }

        if(total<10) {
            total += 2;
        }

        return total;
    }

    public List<Order> getFreeOrders(List<Order> ordini) throws BillException {

        List<Order> ordiniGratis = new ArrayList<Order>();

        for (int i = 0; i < ordini.size(); i++) {

            if(ordini.get(i).getUser().getAge()<18 && //se minorenne
             ordini.get(i).getOrarioOrdine().isAfter(LocalTime.of(18,00,00,00)) && //dopo le 18
             ordini.get(i).getOrarioOrdine().isBefore(LocalTime.of(19,00,00,00))){ //prima delle 19

                ordiniGratis.add(ordini.get(i)); //ordine in regalo

            }
        }

        if(ordiniGratis.size() > 9){

            for(int i=0; i<10; i++) {
              //numero a caso tra 1 e numero ordini
              int randomIndex = (int)(ordiniGratis.size() * Math.random());
              if(ordiniGratis.get(randomIndex).getPrice() == 0) {
                  i--;
              }
              else {
              ordiniGratis.get(randomIndex).setPrice(0);
              }
            }
        }
        else {
            throw new BillException("Ordini insufficienti per regali");
        }

        return ordiniGratis;
    }

}