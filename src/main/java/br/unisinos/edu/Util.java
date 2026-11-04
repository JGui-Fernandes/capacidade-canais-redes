package br.unisinos.edu;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public final class Util {

    public static final Locale LOCALE = Locale.forLanguageTag("pt-BR");

    private Util() {}

    public static XYChart novoGrafico(String titulo, String eixoX, String eixoY) {
        XYChart chart = new XYChartBuilder()
                .width(900).height(550)
                .title(titulo)
                .xAxisTitle(eixoX)
                .yAxisTitle(eixoY)
                .build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        return chart;
    }

    public static void salvar(XYChart chart, String nomeArquivo) throws IOException {
        Files.createDirectories(Path.of("saida"));
        BitmapEncoder.saveBitmapWithDPI(chart, "saida/" + nomeArquivo,
                BitmapEncoder.BitmapFormat.PNG, 300);
        System.out.println("Gráfico salvo em: saida/" + nomeArquivo + ".png");
    }
}
