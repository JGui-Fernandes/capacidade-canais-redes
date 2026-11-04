package br.unisinos.edu;

import org.knowm.xchart.XYChart;

import java.io.IOException;

public class Experimento3 {

    public static void executar() throws IOException {
        double b = 5000;
        int pontos = 9;
        double[] db = new double[pontos];
        double[] c = new double[pontos];

        System.out.println("\n=== EXPERIMENTO 3 - Influência do ruído (B = 5 kHz) ===");
        System.out.printf("%-12s%-18s%-18s%n", "SNR (dB)", "SNR (linear)", "C (bit/s)");
        for (int i = 0; i < pontos; i++) {
            db[i] = i * 5;
            double snr = Capacidade.dbParaLinear(db[i]);
            c[i] = Capacidade.shannon(b, snr);
            System.out.printf(Util.LOCALE, "%-12.0f%-18.2f%,-18.2f%n", db[i], snr, c[i]);
        }

        System.out.println("\nComparação em 10, 20, 30 e 40 dB:");
        double anterior = Double.NaN;
        for (int d = 10; d <= 40; d += 10) {
            double cd = Capacidade.shannon(b, Capacidade.dbParaLinear(d));
            if (Double.isNaN(anterior)) {
                System.out.printf(Util.LOCALE, "%2d dB -> %,.2f bit/s%n", d, cd);
            } else {
                System.out.printf(Util.LOCALE, "%2d dB -> %,.2f bit/s (ganho de %,.2f bit/s sobre %d dB)%n",
                        d, cd, cd - anterior, d - 10);
            }
            anterior = cd;
        }

        XYChart chart = Util.novoGrafico("Shannon: C x SNR (B = 5 kHz)", "SNR (dB)", "C (bit/s)");
        chart.addSeries("B = 5 kHz", db, c);
        Util.salvar(chart, "exp3_ruido");
    }
}
