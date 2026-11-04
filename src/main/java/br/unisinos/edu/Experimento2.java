package br.unisinos.edu;

import org.knowm.xchart.XYChart;

import java.io.IOException;

public class Experimento2 {

    public static void executar() throws IOException {
        double[] snrs = {1000, 5000, 10000, 50000};   // linear
        int pontos = 10;                               // 1..10 kHz
        double[] bKhz = new double[pontos];
        for (int i = 0; i < pontos; i++) bKhz[i] = i + 1;

        double[][] c = new double[snrs.length][pontos];
        double maior = Double.NEGATIVE_INFINITY, menor = Double.POSITIVE_INFINITY;
        String cfgMaior = "", cfgMenor = "";

        for (int s = 0; s < snrs.length; s++) {
            for (int i = 0; i < pontos; i++) {
                c[s][i] = Capacidade.shannon(bKhz[i] * 1000, snrs[s]);
                String cfg = String.format(Util.LOCALE, "B = %.0f kHz, SNR = %,.0f", bKhz[i], snrs[s]);
                if (c[s][i] > maior) { maior = c[s][i]; cfgMaior = cfg; }
                if (c[s][i] < menor) { menor = c[s][i]; cfgMenor = cfg; }
            }
        }

        System.out.println("\n=== EXPERIMENTO 2 - Capacidade de Shannon (bit/s) ===");
        System.out.printf("%-10s", "B (kHz)");
        for (double snr : snrs) System.out.printf(Util.LOCALE, "%16s", String.format(Util.LOCALE, "SNR=%,.0f", snr));
        System.out.println();
        for (int i = 0; i < pontos; i++) {
            System.out.printf(Util.LOCALE, "%-10.0f", bKhz[i]);
            for (int s = 0; s < snrs.length; s++) System.out.printf(Util.LOCALE, "%,16.2f", c[s][i]);
            System.out.println();
        }

        System.out.printf(Util.LOCALE, "%nMaior capacidade: %,.2f bit/s (%s)%n", maior, cfgMaior);
        System.out.printf(Util.LOCALE, "Menor capacidade: %,.2f bit/s (%s)%n", menor, cfgMenor);
        System.out.printf(Util.LOCALE, "Razão C(SNR=5000)/C(SNR=1000) em B=5 kHz: %.3f%n",
                c[1][4] / c[0][4]);

        XYChart chart = Util.novoGrafico("Shannon: C x B", "B (kHz)", "C (bit/s)");
        for (int s = 0; s < snrs.length; s++) {
            chart.addSeries(String.format(Util.LOCALE, "SNR = %,.0f", snrs[s]), bKhz, c[s]);
        }
        Util.salvar(chart, "exp2_shannon");
    }
}
