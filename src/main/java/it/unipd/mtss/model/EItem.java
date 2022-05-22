////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {
    ItemType itemType; 
    String name; 
    double price; 

    public EItem(ItemType itemType, String name, double price) {
        if(itemType == null) {
            throw new IllegalArgumentException("Elemento nullo");
        }
        if(name == null) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if(price <= 0) {
            throw new IllegalArgumentException("Prezzo non valido");
        }
        this.itemType = itemType; 
        this.name = name; 
        this.price = price; 
    }

    public double getPrice() {
        return price; 
    }

    public ItemType getType() {
        return itemType;
    }

    public String getName() {
        return name;
    }
}