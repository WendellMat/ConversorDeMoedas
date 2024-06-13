import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        ConsultaAPI consulta = new ConsultaAPI();
        Scanner sc = new Scanner(System.in);

        String moedaBase = null;
        String moedaDestino = null;
        double valorDeCambio = 0;
        int opcao = 0;
        while (opcao != 7) {
            System.out.println("""
                    ***************************************************************
                    Seja bem-vindo(a) ao conversor de moedas! :)
                    
                    1) Real ===> Peso Argentino
                    2) Real ===> Peso Chileno
                    3) Real ===> Peso Colombiano
                    4) Peso Colombiano ===> Real
                    5) Peso Chileno ===> Real
                    6) Peso Argentino ===> Real
                    7) Sair
                    Escolha uma opção válida:
                    ***************************************************************
                    """);
            opcao = sc.nextInt();
            if (opcao >= 1 && opcao <= 6) {
                switch (opcao) {
                    case 1:
                        moedaBase = "BRL";
                        moedaDestino = "ARS";
                        break;
                    case 2:
                        moedaBase = "BRL";
                        moedaDestino = "CLP";
                        break;
                    case 3:
                        moedaBase = "BRL";
                        moedaDestino = "COP";
                        break;
                    case 4:
                        moedaBase ="COP";
                        moedaDestino = "BRL";
                        break;
                    case 5:
                        moedaBase ="CLP";
                        moedaDestino = "BRL";
                        break;
                    case 6:
                        moedaBase ="ARS";
                        moedaDestino = "BRL";
                        break;
                }
                valorDeCambio = consulta.consultaCambio(moedaBase, moedaDestino);
                System.out.println("Digite o valor que deseja converter:");
                double valorDesejado = sc.nextDouble();
                double valorFinal = valorDesejado * valorDeCambio;
                System.out.printf("O valor de " + valorDesejado + " [" + moedaBase + "] corresponde ao valor final de ==> " + valorFinal + " [" + moedaDestino + "]\n");
            }
        }
        System.out.println("Finalizando Programa!");
    }
}
