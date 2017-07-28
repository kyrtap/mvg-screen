package de.kyrtap5.mvgscreen;


import java.util.Date;

class Departure implements Comparable<Departure> {
    private String line;
    private Transport.Type type;
    private String destination;
    private Date departure;

    Departure(String line, Transport.Type type, String destination, Date departure) {
        this.line = line;
        this.type = type;
        this.destination = destination;
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Linie: " + line + " (" + type.toString() + ")\t" +
                "Ziel: " + destination + "\t" +
                "Abfahrt: " + DateHandler.formatDate(departure, "HH:mm") + " (" + DateHandler.getDifference(departure, new Date()) + ")";
    }

    @Override
    public int compareTo(Departure other) {
        return departure.compareTo(other.departure);
    }

    public Transport.Type getType() {
        return type;
    }

    public String getLine() {
        return line;
    }
    public String getDestination() {
        return destination;
    }

    public Date getDeparture() {
        return departure;
    }
}
