import javax.swing.JOptionPane;

public class App {
    public static String[] moedaEscolhida(String escolha) {
        if (escolha.equals("Real para Dólar")) {
            return new String[]{"brl-usd", "$"};
        } else if (escolha.equals("Real para Euro")) {
            return new String[]{"brl-eur", "€"};
        } else if (escolha.equals("Real para Libra")) {
            return new String[]{"brl-gbp", "£"};
        } else if (escolha.equals("Real para Peso Argentino")) {
            return new String[]{"brl-ars", "$"};
        } else if (escolha.equals("Real para Peso Chileno")) {
            return new String[]{"brl-clp", "$"};
        } else if (escolha.equals("Dólar para Real")) {
            return new String[]{"usd", "R$"};
        } else if (escolha.equals("Euros para Real")) {
            return new String[]{"eur", "R$"};
        } else if (escolha.equals("Libra para Real")) {
            return new String[]{"gbp", "R$"};
        } else if (escolha.equals("Peso Argentino para Reais")) {
            return new String[]{"ars", "R$"};
        } else if (escolha.equals("Dólar para Peso Chileno")) {
            return new String[]{"clp", "R$"};
        }
        return new String[]{};
    }

    public static boolean converterMoeda() {
        coin coinOrigem = new coin();
        coin coinDestino = new coin();

        String valorInput = JOptionPane.showInputDialog("Digite o valor a ser convertido:");

        return escolhaDaConversao(coinOrigem, coinDestino, valorInput);
    }

    private static boolean escolhaDaConversao(coin coinOrigem, coin coinDestino, String valorInput) {
        int desejaContinuar;
        if (valorInput != null) {
            try {
                double valor = Double.parseDouble(valorInput);
                coinOrigem.setValor(valor);
                desejaContinuar = escolhaDaMoeda(coinOrigem, coinDestino);
                return continuar(desejaContinuar);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                desejaContinuar = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Escolha uma Opção", 1);
                return continuar(desejaContinuar);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Programa finalizado!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public static int escolhaDaMoeda(coin coinOrigem, coin coinDestino) {
        Object[] moedas = {"Real para Dólar", "Real para Euro", "Real para Libra",
                "Real para Argentino", "Real para Peso Chileno", "De Dólar para Real",
                "Euro para Real", "Libra para Real", "Peso Argentino para Real",
                "Peso Chileno para Real"};

        Object escolhaMoeda = JOptionPane.showInputDialog(
                null,
                "Selecione a moeda para converção:",
                "Moedas",
                JOptionPane.DEFAULT_OPTION,
                null, moedas,
                null
        );

        if (escolhaMoeda == null) {
            JOptionPane.showMessageDialog(null, "Programa finalizado", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        } else {
            String escolhaMoedaString = escolhaMoeda.toString();
            coinDestino.setCodigo(moedaEscolhida(escolhaMoedaString)[0]);
            coinDestino.setSimbolo(moedaEscolhida(escolhaMoedaString)[1]);

            Converter converter = new Converter();

            coinDestino.setValor(converter.converterMoeda(coinOrigem, coinDestino.getCodigo()));
            String conversaoFinal = "O valor convertido é de " + coinDestino.getSimbolo() + " " + coinDestino.getValor();
            JOptionPane.showMessageDialog(null, conversaoFinal, "Mensagem", JOptionPane.INFORMATION_MESSAGE);

            int desejaContinuar = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Escolha uma Opção", 1);
            return desejaContinuar;
        }
    }

    //public static void converterTemperatura() {
      //  JOptionPane.showMessageDialog(null, "Programa em construção", "Message", JOptionPane.INFORMATION_MESSAGE);

//        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite a temperatura"));
        // Adicione o código para conversão de temperatura aqui
   // }

    public static boolean continuar(int escolha) {
        if (escolha == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            Object[] optMenu = {"Conversor de Moeda"};
            Object escolhaMenu = JOptionPane.showInputDialog(
                    null,
                    "Menu Incial",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    null,
                    optMenu,
                    null);

            if (escolhaMenu == null) {
                JOptionPane.showMessageDialog(null, "Programa finalizado", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                continuar = false;
            } else if (escolhaMenu.equals("Conversor de Moeda")) {
                continuar = converterMoeda();
            }
        }
    }
}
