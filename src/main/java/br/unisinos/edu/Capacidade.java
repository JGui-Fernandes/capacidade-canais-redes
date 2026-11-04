package br.unisinos.edu;

public final class Capacidade {

    private Capacidade() {}

    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    /** Nyquist: C = 2B log2(N). Retorna bit/s. */
    public static double nyquist(double larguraBandaHz, int niveis) {
        if (larguraBandaHz <= 0 || niveis < 1) {
            throw new IllegalArgumentException("B deve ser > 0 e N >= 1");
        }
        return 2 * larguraBandaHz * log2(niveis);
    }

    /** Shannon: C = B log2(1 + SNR), SNR em escala linear. Retorna bit/s. */
    public static double shannon(double larguraBandaHz, double snrLinear) {
        if (larguraBandaHz <= 0 || snrLinear < 0) {
            throw new IllegalArgumentException("B deve ser > 0 e SNR >= 0");
        }
        return larguraBandaHz * log2(1 + snrLinear);
    }

    /** SNR = 10^(dB/10). */
    public static double dbParaLinear(double db) {
        return Math.pow(10, db / 10.0);
    }
}