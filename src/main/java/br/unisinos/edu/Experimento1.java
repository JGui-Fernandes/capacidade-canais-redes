package br.unisinos.edu;

import org.knowm.xchart.XYChart;

import java.io.IOException;

public class Experimento1 {

    public static void executar() throws IOException {
        double[] bandas = {1000, 5000, 10000};   // Hz
        int[] niveis = {2, 4, 8, 16};

        System.out.println("\n=== EXPERIMENTO 1 - Capacidade de Nyquist (bit/s) ===");
        System.out.printf("%-10s", "B \\ N");
        for (int n : niveis) System.out.printf("%14s", "N=" + n);
        System.out.println();

        XYChart chart = Util.novoGrafico("Nyquist: C x N", "N (níveis)", "C (bit/s)");
        double[] xs = new double[niveis.length];
        for (int i = 0; i < niveis.length; i++) xs[i] = niveis[i];

        for (double b : bandas) {
            double[] ys = new double[niveis.length];
            System.out.printf(Util.LOCALE, "%-10s", String.format(Util.LOCALE, "%.0f kHz", b / 1000));
            for (int i = 0; i < niveis.length; i++) {
                ys[i] = Capacidade.nyquist(b, niveis[i]);
                System.out.printf(Util.LOCALE, "%,14.0f", ys[i]);
            }
            System.out.println();
            chart.addSeries(String.format(Util.LOCALE, "B = %.0f kHz", b / 1000), xs, ys);
        }

        Util.salvar(chart, "exp1_nyquist");
    }
}