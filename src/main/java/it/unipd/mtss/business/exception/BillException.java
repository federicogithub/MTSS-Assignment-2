////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business.exception;

public class BillException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BillException(String msg) {
        super(msg);
    }

}