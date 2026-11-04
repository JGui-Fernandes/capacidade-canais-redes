# Capacidade de Canais de Comunicação

Atividade Prática 10 da disciplina de Redes de Computadores (UNISINOS).

O programa implementa os modelos de capacidade de canal de **Nyquist** e **Shannon**, calcula as capacidades de quatro experimentos, imprime as tabelas no console e gera os gráficos em PNG.

## Modelos

| Modelo | Fórmula | Considera ruído? |
|---|---|---|
| Nyquist | `C = 2B · log₂(N)` | Não (canal ideal) |
| Shannon | `C = B · log₂(1 + SNR)` | Sim |

- `C`: capacidade (bit/s)
- `B`: largura de banda (Hz)
- `N`: número de níveis de sinalização
- `SNR`: relação sinal-ruído em escala linear (`SNR = 10^(dB/10)`)

## Experimentos

| # | Descrição | Parâmetros | Saída |
|---|---|---|---|
| 1 | **Capacidade de Nyquist**: efeito de B e N em um canal sem ruído | B = 1, 5, 10 kHz; N = 2, 4, 8, 16 | Tabela e `exp1_nyquist.png` |
| 2 | **Capacidade de Shannon**: efeito de B e da SNR; identifica a maior e a menor capacidade | SNR = 1.000, 5.000, 10.000, 50.000; B de 1 a 10 kHz | Tabela e `exp2_shannon.png` |
| 3 | **Influência do ruído**: converte SNR de dB para linear e mostra o efeito de +10 dB | B = 5 kHz; SNR de 0 a 40 dB (passo de 5) | Tabela e `exp3_ruido.png` |
| 4 | **Nyquist × Shannon**: compara a taxa de Nyquist com o limite de Shannon e identifica onde Nyquist o ultrapassa | B = 3 kHz; SNR = 30 dB; N = 2 a 64 | Tabela e `exp4_nyquist_vs_shannon.png` |

## Estrutura do projeto

```
capacidade-canais/
├── pom.xml
├── README.md
├── saida/                      # gráficos gerados (PNG)
└── src/main/java/br/unisinos/edu/
    ├── Main.java               # executa os 4 experimentos em sequência
    ├── Capacidade.java         # fórmulas de Nyquist, Shannon e conversão dB → linear
    ├── Util.java               # criação e gravação dos gráficos
    ├── Experimento1.java
    ├── Experimento2.java
    ├── Experimento3.java
    └── Experimento4.java
```

## Requisitos

- JDK 17 ou superior
- Maven 3.6 ou superior

A biblioteca de gráficos [XChart](https://github.com/knowm/XChart) é baixada automaticamente pelo Maven.

## Como executar

Na raiz do projeto (onde está o `pom.xml`):

```bash
mvn compile exec:java
```

As tabelas são impressas no console e os gráficos são salvos na pasta `saida/`.

Para salvar as tabelas em um arquivo de texto:

```bash
mvn -q compile exec:java > saida/saida_tabelas.txt
```

### Executando pelo IntelliJ

Abra o projeto como Maven, abra a classe `Main` e clique em **Run**.