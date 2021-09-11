import java.nio.file.Files;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Path caminhoEntrada = Paths.get("src\\prog.txt");
        Path caminhoSaida = Paths.get("src\\prog-check.txt");
        ArrayList<String> linesCorrigidas = new ArrayList<>();
        List<String> linhasInicio = Files.readAllLines(caminhoEntrada);

        int cont = 0;

        for (String linha : linhasInicio) {

            if (CorrigirLinha(linha)) {
                linesCorrigidas.add(cont, linha + "  -Ok");
            } else {
                linesCorrigidas.add(cont, linha + "  -Invalido");
            }
            cont++;
        }
        Files.write(caminhoSaida, linesCorrigidas);
    }

    public static boolean CorrigirLinha(String linha) {
        Stack<Character> pilha = new Stack<>();

        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == '(' || linha.charAt(i) == '[' || linha.charAt(i) == '<' || linha.charAt(i) == '{') {
                pilha.push(linha.charAt(i));
            } else {
                if (pilha.isEmpty())
                    return false;
                if (pilha.peek() == '(' && linha.charAt(i) != ')' || pilha.peek() == '[' && linha.charAt(i) != ']'
                        || pilha.peek() == '<' && linha.charAt(i) != '>'
                        || pilha.peek() == '{' && linha.charAt(i) != '}') {
                    return false;
                }
                pilha.pop();
            }
        }
        return pilha.isEmpty();
    }
}
