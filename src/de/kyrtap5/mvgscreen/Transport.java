package de.kyrtap5.mvgscreen;

class Transport {
    public enum Type {
        UBAHN, SBAHN, TRAM, BUS, EXPRESSBUS, NACHTBUS
    }

    /**
     * Returns the Munich transport type from the given line string
     *
     * @param input the line String
     * @return the transport type
     */
    static Type getTransportType(String input) {
        if (input.startsWith("U")) return Type.UBAHN;
        else if (input.startsWith("S")) return Type.SBAHN;
        else if (input.startsWith("X")) return Type.EXPRESSBUS;
        else if (input.startsWith("N")) return Type.NACHTBUS;
        else {
            if (Integer.parseInt(input) < 30) return Type.TRAM;
            else return Type.BUS;
        }
    }
}
