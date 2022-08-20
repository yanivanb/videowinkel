package be.vdab.videowinkel.exceptions;

public class ReserveerFilmsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ReserveerFilmsException(String message) {
        super(message);
    }

    public ReserveerFilmsException(String message, Exception oorzaak) {
        super(message, oorzaak);
    }
}
