package br.unisinos.edu;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;

public class Experimento4 {

    public static void executar() throws IOException {
        double b = 3000;      // Hz
        double snrDb = 30;
        int[] niveis = {2, 4, 8, 16, 32, 64};

        double limite = Capacidade.shannon(b, Capacidade.dbParaLinear(snrDb));

        System.out.println("\n=== EXPERIMENTO 4 - Nyquist x Shannon (B = 3 kHz, SNR = 30 dB) ===");
        System.out.printf(Util.LOCALE, "Limite de Shannon: %,.2f bit/s%n%n", limite);
        System.out.printf("%-6s%-20s%-20s%-12s%n", "N", "Nyquist (bit/s)", "Shannon (bit/s)", "Excede?");

        double[] xs = new double[niveis.length];
        double[] nyq = new double[niveis.length];
        double[] sha = new double[niveis.length];

        for (int i = 0; i < niveis.length; i++) {
            xs[i] = niveis[i];
            nyq[i] = Capacidade.nyquist(b, niveis[i]);
            sha[i] = limite;
            System.out.printf(Util.LOCALE, "%-6d%,-20.2f%,-20.2f%-12s%n",
                    niveis[i], nyq[i], limite, nyq[i] > limite ? "SIM" : "não");
        }

        XYChart chart = Util.novoGrafico("Nyquist x Shannon (B = 3 kHz, SNR = 30 dB)",
                "N (níveis)", "C (bit/s)");
        chart.addSeries("Nyquist", xs, nyq);
        chart.addSeries("Limite de Shannon", xs, sha).setMarker(SeriesMarkers.NONE);
        Util.salvar(chart, "exp4_nyquist_vs_shannon");
    }
}
